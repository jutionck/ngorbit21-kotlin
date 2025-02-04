package com.example.ngorbit21androidkotlin

import android.app.Application
import com.example.ngorbit21androidkotlin.di.appModule
import org.koin.core.context.startKoin

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}