package com.example.finance.di

import android.app.Application
import com.example.finance.BuildConfig
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }
    companion object {
        lateinit var appComponent: AppComponent
    }
}