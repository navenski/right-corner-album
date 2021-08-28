package com.navektest.feature_album_list.repository

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.feature_album_list.repository.datasource.mapper.AlbumEntityMapper
import com.navektest.feature_album_list.repository.datasource.remote.AlbumRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Factory of [AlbumListRepository]
 * In order to create an [AlbumListRepository] a [CoroutineScope] is needed
 */
class AlbumListRepositoryFactory @Inject constructor(private val dispatcherProvider: CoroutineDispatcherProvider,
                                                     private val remoteDataSource: AlbumRemoteDataSource,
                                                     private val localDataSource: AlbumLocalDatasource,
                                                     private val entityMapper: AlbumEntityMapper) {

    fun create(coroutineScope: CoroutineScope): AlbumListRepository {
        return AlbumListRepository(dispatcherProvider,
                                   coroutineScope,
                                   remoteDataSource,
                                   localDataSource,
                                   entityMapper)
    }
}