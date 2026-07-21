package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignInResponse
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.ApiService
import kotlinx.coroutines.CancellationException

interface AuthRepository {
    suspend fun signIn(request: SignInRequest): Result<SignInResponse>

    suspend fun signUp(request: SignUpRequest): Result<User>
}

class NetworkAuthRepository(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun signIn(request: SignInRequest): Result<SignInResponse> {
        return runCatching {
            val response = apiService.signIn(request)
            // TODO: handle success=false with specific status codes
            response.payload ?: throw IllegalStateException("Empty response payload")
        }
    }

    override suspend fun signUp(request: SignUpRequest): Result<User> {
        return try {
            val response = apiService.signUp(request)

            if (!response.success) {
                throw Exception(getSignUpErrorMessage(response.statusCode))
            }

            val user = response.payload?.data ?: throw IllegalStateException("Empty response payload")

            Result.success(user)
        } catch(e: CancellationException) {
            throw e
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    private fun getSignUpErrorMessage(code: Int): String {
        return when (code) {
            400 -> "Missing any of the required parameters"
            405 -> "The endpoint does not support the HTTP method specified"
            409 -> "Requested user already has some of the parameters in the database"
            500 -> "Internal server error (cooked)"
            else -> "Unknown API error with code: $code"
        }
    }
}
