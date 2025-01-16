package com.example.audiobooks

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//Access to variables across the Application
open class PodcastsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PodcastsApp)
            modules(appModule)
        }
    }
}