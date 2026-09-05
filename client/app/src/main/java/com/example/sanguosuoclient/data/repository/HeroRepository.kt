package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.Hero
import com.example.sanguosuoclient.data.remote.ApiService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface HeroRepository {
    val savedHeroes: StateFlow<List<Hero>>
    suspend fun searchHeroesByName(token: String, name: String): Result<List<Hero>>
    suspend fun getHeroById(token: String, heroId: String): Result<Hero>
    suspend fun saveHero(token: String, userId: String, hero: Hero): Result<Unit>
    suspend fun getSavedHeroes(token: String, userId: String): Result<List<Hero>>
    suspend fun unsaveHero(token: String, userId: String, heroId: String): Result<Unit>
}

class NetworkHeroRepository(
    private val apiService: ApiService
) : HeroRepository {

    private val _savedHeroes = MutableStateFlow<List<Hero>>(emptyList())
    override val savedHeroes: StateFlow<List<Hero>> = _savedHeroes.asStateFlow()

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

    override suspend fun getSavedHeroes(token: String, userId: String): Result<List<Hero>> {
        return try {
            val response = apiService.getSavedHeroes(token, userId)
            if (!response.success) {
                throw Exception("Fetch saved heroes failed (${response.statusCode})")
            }
            val heroes = response.payload?.data ?: emptyList()
            _savedHeroes.value = heroes
            Result.success(heroes)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveHero(token: String, userId: String, hero: Hero): Result<Unit> {
        if (_savedHeroes.value.any { it.id == hero.id }) {
            return Result.success(Unit) // already saved, no-op
        }

        val previous = _savedHeroes.value
        _savedHeroes.value = previous + hero // optimistic add

        return try {
            val response = apiService.saveHero(token, userId, hero.id)
            if (!response.success) {
                throw Exception("Save hero failed (${response.statusCode})")
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _savedHeroes.value = previous // roll back
            Result.failure(e)
        }
    }

    override suspend fun unsaveHero(token: String, userId: String, heroId: String): Result<Unit> {
        val previous = _savedHeroes.value
        _savedHeroes.value = previous.filterNot { it.id == heroId } // optimistic remove

        return try {
            val response = apiService.unsaveHero(token, userId, heroId)
            if (!response.success) {
                throw Exception("Unsave hero failed (${response.statusCode})")
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _savedHeroes.value = previous // roll back
            Result.failure(e)
        }
    }
}