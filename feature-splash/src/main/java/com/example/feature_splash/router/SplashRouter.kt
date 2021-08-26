package com.example.feature_splash.router

import android.app.Activity
import com.navektest.core_navigation.startScreen.StartScreenNavigation
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * Handle all  navigation for the Splash feature
 */
class SplashRouter @Inject constructor ( private val context: Activity, private val startScreenNavigation: StartScreenNavigation) {

    /**
     * Navigate to the startScreen.
     *
     */
    fun navigate() {
        startScreenNavigation.navigate(context)
    }

    /**
     * finish the SplashActivy
     */
    fun close() {
        context.finish()
    }
}