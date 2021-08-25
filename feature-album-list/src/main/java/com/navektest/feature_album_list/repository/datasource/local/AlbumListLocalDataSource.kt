package com.navektest.feature_album_list.repository.datasource.local

import androidx.paging.PagingSource
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_database.daos.AlbumAndPictureDao
import com.navektest.core_database.daos.AlbumDao
import com.navektest.core_database.daos.PictureDao
import com.navektest.core_database.entities.AlbumEntity
import com.navektest.core_database.entities.PictureEntity
import kotlinx.coroutines.withContext

class AlbumListLocalDataSource(private val dispatcherProvider: CoroutineDispatcherProvider,
                               private val albumDao: AlbumDao) {
    suspend fun saveEntities(entities: List<AlbumEntity>) = withContext(dispatcherProvider.io()) {
        albumDao.insert(entities)
    }

    fun getPagedAlbums(): PagingSource<Int, AlbumEntity> = albumDao.getPagedAlbums()

     fun hasAnyAlbum() = albumDao.hasAnyAlbum()
}