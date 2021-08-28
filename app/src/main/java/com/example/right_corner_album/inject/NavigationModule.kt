package com.example.right_corner_album.inject

import android.app.Activity
import com.example.right_corner_album.navigation.AlbumDetailNavigationImpl
import com.example.right_corner_album.navigation.BackNavigationImpl
import com.example.right_corner_album.navigation.StartScreenNavigationImpl
import com.navektest.core_navigation.albumdetail.AlbumDetailNavigation
import com.navektest.core_navigation.back.BackNavigation
import com.navektest.core_navigation.startScreen.StartScreenNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    fun provideAlbumListNavigation(activity: Activity): StartScreenNavigation = StartScreenNavigationImpl(activity)

    @Provides
    fun provideAlbumDetailNavigation(activity: Activity) : AlbumDetailNavigation = AlbumDetailNavigationImpl(activity)

    @Provides
    fun provideBackNavigation(activity: Activity) : BackNavigation = BackNavigationImpl(activity)
}