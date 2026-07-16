package com.example.sanguosuoclient.di

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.sanguosuoclient.data.remote.RetrofitClient
import com.example.sanguosuoclient.data.repository.AuthRepository
import com.example.sanguosuoclient.data.repository.AuthRepositoryImpl

class AppContainer {
    private val apiService = RetrofitClient.create()

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(apiService)
    }
}

val LocalAppContainer = staticCompositionLocalOf<AppContainer> {
    error("No AppContainer provided")
}
