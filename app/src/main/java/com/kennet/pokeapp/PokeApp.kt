package com.kennet.pokeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class PokeApp :Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG){
            Timber.plant(DebugTree())
//        }
    }
}