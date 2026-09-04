package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.UserInfo
import com.example.sanguosuoclient.data.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.cancellation.CancellationException

interface UserRepository {
    val currentUser: StateFlow<UserInfo?>
    suspend fun fetchUserById(token: String, userId: String): Result<UserInfo>
}

class NetworkUserRepository(
    private val apiService: ApiService
) : UserRepository {

    private val _currentUser = MutableStateFlow<UserInfo?>(null)
    override val currentUser: StateFlow<UserInfo?> = _currentUser.asStateFlow()

    override suspend fun fetchUserById(token: String, userId: String): Result<UserInfo> {
        return try {
            val response = apiService.getUserById(token = token, userId = userId)
            if (!response.success) {
                throw Exception("Failed to fetch user info (${response.statusCode})")
            }
            val userInfo = response.payload?.data ?: throw IllegalStateException("Empty response payload")
            _currentUser.value = userInfo
            Result.success(userInfo)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}