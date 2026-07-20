package com.example.sanguosuoclient.data.remote

import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignInResponse
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.dto.ServiceResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): ServiceResponse<SignInResponse>

    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): ServiceResponse<User>
}
