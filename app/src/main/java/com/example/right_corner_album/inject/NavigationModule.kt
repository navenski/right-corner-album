package com.example.right_corner_album.inject

import android.app.Activity
import com.example.right_corner_album.navigation_impl.AlbumDetailNavigationImpl
import com.example.right_corner_album.navigation_impl.BackNavigationImpl
import com.example.right_corner_album.navigation_impl.StartScreenNavigationImpl
import com.navektest.core_navigation.albumdetail.AlbumDetailNavigation
import com.navektest.core_navigation.back.BackNavigation
import com.navektest.core_navigation.startScreen.StartScreenNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

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