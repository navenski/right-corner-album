package com.navektest.feature_album_list.inject

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.NetworkStateAvailability
import com.navektest.core_database.AppDatabase
import com.navektest.feature_album_list.repository.AlbumListRepositoryFactory
import com.navektest.feature_album_list.repository.datasource.local.AlbumListLocalDataSource
import com.navektest.feature_album_list.repository.datasource.mapper.AlbumEntityMapper
import com.navektest.feature_album_list.repository.datasource.remote.AlbumApi
import com.navektest.feature_album_list.repository.datasource.remote.AlbumRemoteDataSource
import com.navektest.feature_album_list.repository.mapper.AlbumItemMapper
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