package com.example.feature_splash.inject

import android.app.Activity
import android.content.Context
import com.example.feature_splash.router.SplashRouter
import com.navektest.core_navigation.startScreen.StartScreenNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object SplashActivityModule {
    @Provides
    fun provideSplashRouter(@ActivityContext context: Context, navigation: StartScreenNavigation): SplashRouter =
        SplashRouter(context as Activity, navigation)
}