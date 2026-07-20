package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignInResponse
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.ApiService

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
        return runCatching {
            val response = apiService.signUp(request)
            // TODO: handle success=false with specific status codes
            response.payload ?: throw IllegalStateException("Empty response payload")
        }
    }
}
