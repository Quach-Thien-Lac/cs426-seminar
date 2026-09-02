package com.example.sanguosuoclient.data.repository

import com.example.sanguosuoclient.data.model.SignInData
import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.ApiService
import com.example.sanguosuoclient.data.session.SessionManager
import com.example.sanguosuoclient.data.session.UserSession
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException

interface AuthRepository {
    suspend fun signIn(request: SignInRequest): Result<SignInData>

    suspend fun signUp(request: SignUpRequest): Result<User>
    suspend fun signOut(): Unit
}

class NetworkAuthRepository(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : AuthRepository {
    override suspend fun signIn(request: SignInRequest): Result<SignInData> {
        return try {
            val response = apiService.signIn(request)
            if (!response.success) {
                throw Exception(getSignInErrorMessage(response.statusCode))
            }
            val data = response.payload?.data ?: throw IllegalStateException("Empty response payload")

            sessionManager.login(
                UserSession(
                    userId = data.userID,
                    token = data.sessionToken
                )
            )

            Result.success(data)
        }
        catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut() {
        sessionManager.logout()
    }

    private fun getSignInErrorMessage(code: Int): String {
        return when(code) {
            400 -> "Missing any of the required parameters"
            401 -> "Wrong password"
            404 -> "The username does not exist"
            405 -> "The endpoint does not support the HTTP method specified"
            500 -> "Internal server error (cooked)"
            else -> "Unknown API error with code: $code"
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
