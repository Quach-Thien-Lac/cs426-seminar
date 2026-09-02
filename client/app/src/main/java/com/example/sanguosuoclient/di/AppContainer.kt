package com.example.sanguosuoclient.di

import android.content.Context
import android.net.Network
import android.se.omapi.Session
import com.example.sanguosuoclient.data.remote.ApiService
import com.example.sanguosuoclient.data.repository.AuthRepository
import com.example.sanguosuoclient.data.repository.HeroRepository
import com.example.sanguosuoclient.data.repository.NetworkAuthRepository
import com.example.sanguosuoclient.data.repository.NetworkHeroRepository
import com.example.sanguosuoclient.data.session.SessionManager
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json

interface AppContainer {
    val authRepository: AuthRepository
    val heroRepository: HeroRepository
    val sessionManager: SessionManager
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val baseUrl = "http://10.0.2.2:8080/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val sessionManager : SessionManager by lazy {
        SessionManager(context = context)
    }

    override val authRepository: AuthRepository by lazy {
        NetworkAuthRepository(sessionManager = sessionManager, apiService = retrofitService)
    }

    override val heroRepository: HeroRepository by lazy {
        NetworkHeroRepository(retrofitService)
    }
}
