package com.navektest.feature_album_list.inject

import com.navektest.feature_album_list.repository.datasource.remote.AlbumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object AlbumListViewModelModule {
    @Provides
    fun provideAlbumApi(retrofit: Retrofit): AlbumApi = retrofit.create(AlbumApi::class.java)
}