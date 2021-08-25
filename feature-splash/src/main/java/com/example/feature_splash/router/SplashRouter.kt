package com.example.feature_splash.router

import android.app.Activity
import com.navektest.core_navigation.startScreen.StartScreenNavigation

/**
 * Handle all  navigation for the Splash feature
 */
class SplashRouter(private val context: Activity, private val startScreenNavigation: StartScreenNavigation) {

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