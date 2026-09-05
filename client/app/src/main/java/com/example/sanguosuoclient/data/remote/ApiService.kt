package com.example.sanguosuoclient.data.remote


import com.example.sanguosuoclient.data.model.HeroListPayload
import com.example.sanguosuoclient.data.model.SaveHeroPayload
import com.example.sanguosuoclient.data.model.SignInPayload
import com.example.sanguosuoclient.data.model.SignInRequest
import com.example.sanguosuoclient.data.model.SignUpPayload
import com.example.sanguosuoclient.data.model.SignUpRequest
import com.example.sanguosuoclient.data.model.User
import com.example.sanguosuoclient.data.model.UserInfoPayload
import com.example.sanguosuoclient.data.remote.dto.ServiceResponse
import com.example.sanguosuoclient.ui.navigation.NavRoute
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @GET("api/heroes/id/{heroId}")
    suspend fun searchHeroesById(
        @Header("Authorization") token: String,
        @Path("heroId") heroId: String
    ): ServiceResponse<HeroListPayload>

    @GET("api/user/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") userId: String
    ): ServiceResponse<UserInfoPayload>

    @POST("api/heroes/save/{userId}/{heroId}")
    suspend fun saveHero(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("heroId") heroId: String
    ): ServiceResponse<SaveHeroPayload>

    @GET("api/heroes/saved/{userId}")
    suspend fun getSavedHeroes(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): ServiceResponse<HeroListPayload>

    @DELETE("api/heroes/unsave/{userId}/{heroId}")
    suspend fun unsaveHero(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Path("heroId") heroId: String
    ): ServiceResponse<SaveHeroPayload>
}
