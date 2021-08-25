package com.navektest.feature_album_detail.repository.localdatasource

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_database.daos.AlbumDao
import com.navektest.core_database.entities.AlbumEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumDetailLocalDataSource @Inject constructor(private val contextDispatcherProvider: CoroutineDispatcherProvider,
                                                     private val albumDao: AlbumDao) {

    suspend fun getAlbum(id: Long): AlbumEntity = withContext(contextDispatcherProvider.io()) { albumDao.get(id) }
}