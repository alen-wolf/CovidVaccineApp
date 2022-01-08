package com.example.covidvaccine

import android.app.Application
import timber.log.Timber

class CovidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}