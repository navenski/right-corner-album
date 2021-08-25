package com.example.right_corner_album.inject

import com.example.right_corner_album.navigation.AlbumDetailNavigationImpl
import com.example.right_corner_album.navigation.StartScreenNavigationImpl
import com.navektest.core_navigation.albumdetail.AlbumDetailNavigation
import com.navektest.core_navigation.startScreen.StartScreenNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideAlbumListNavigation(): StartScreenNavigation = StartScreenNavigationImpl()

    @Singleton
    @Provides
    fun provideAlbumDetailNavigation() : AlbumDetailNavigation = AlbumDetailNavigationImpl()
}