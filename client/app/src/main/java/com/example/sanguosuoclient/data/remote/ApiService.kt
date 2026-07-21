package com.example.sanguosuoclient.data.remote

import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignInResponse
import com.example.sanguosuoclient.data.model.SignUpPayload
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.dto.ServiceResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): ServiceResponse<SignInResponse>

    @Headers("Content-Type:application/json")
    @POST("api/auth/register")
    suspend fun signUp(@Body request: SignUpRequest): ServiceResponse<SignUpPayload>
}
