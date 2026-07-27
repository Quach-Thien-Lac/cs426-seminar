package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.remote.ApiService
import kotlinx.coroutines.CancellationException

interface HeroRepository {
    suspend fun searchHeroesByName(token: String, name: String): Result<List<Hero>>
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
}
