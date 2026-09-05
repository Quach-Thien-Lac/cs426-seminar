package com.example.sanguosuoclient

import android.app.Application
import com.example.sanguosuoclient.di.AppContainer
import com.example.sanguosuoclient.di.DefaultAppContainer

class SanguosuoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}