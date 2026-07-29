package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.remote.ApiService
import kotlinx.coroutines.CancellationException

interface HeroRepository {
    suspend fun searchHeroesByName(token: String, name: String): Result<List<Hero>>
    suspend fun getHeroById(token: String, heroId: String): Result<Hero>
}

class NetworkHeroRepository(
    private val apiService: ApiService
) : HeroRepository {
    override suspend fun searchHeroesByName(token: String, name: String): Result<List<Hero>> {
        return try {
            val response = apiService.searchHeroesByName(token, name)
            if (!response.success) {
                throw Exception("Search failed (${response.statusCode})")
            }
            val heroes = response.payload?.data ?: emptyList()
            Result.success(heroes)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHeroById(token: String, heroId: String): Result<Hero> {
        return try {
            val response = apiService.searchHeroesById(token, heroId)
            if (!response.success) {
                throw Exception("Hero fetch failed (${response.statusCode})")
            }
            val hero = response.payload?.data?.firstOrNull() ?: throw Exception("Hero not found")
            Result.success(hero)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
