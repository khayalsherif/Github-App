package com.example.android.codelabs.paging

import android.app.Application
import com.example.android.codelabs.paging.di.dataModule
import com.example.android.codelabs.paging.di.domainModule
import com.example.android.codelabs.paging.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val modules = listOf(dataModule, domainModule, presentationModule)
            properties(mapOf("base" to "https://api.github.com/"))
            modules(modules)
            androidContext(this@App)
        }
    }
}