package com.example.right_corner_album

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

import timber.log.Timber.*

@HiltAndroidApp
class RightCornerAlbumApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}