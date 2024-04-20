package com.alexisdev.flightsearch

import android.app.Application
import com.alexisdev.flightsearch.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger(Level.ERROR)
            androidContext(this@App)
            modules(koinModules)
        }
    }
}