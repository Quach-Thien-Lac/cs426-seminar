package com.example.sanguosuoclient.data.remote

import com.example.sanguosuoclient.data.model.HeroListPayload
import com.example.sanguosuoclient.data.model.SignInPayload
import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignUpPayload
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.remote.dto.ServiceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type:application/json")
    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignInRequest): ServiceResponse<SignInPayload>

    @Headers("Content-Type:application/json")
    @POST("api/auth/register")
    suspend fun signUp(@Body request: SignUpRequest): ServiceResponse<SignUpPayload>

    @GET("api/heroes/name/{heroName}")
    suspend fun searchHeroesByName(
        @Header("Authorization") token: String,
        @Path("heroName") heroName: String
    ): ServiceResponse<HeroListPayload>
}
