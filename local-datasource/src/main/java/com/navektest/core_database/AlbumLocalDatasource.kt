package com.navektest.core_database

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.navektest.core_database.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDatasource {
    suspend fun get(id: Long): Album
    suspend fun save(albums: List<Album>)
    fun getPagedAlbums(pageSize: Int, maxSize: Int): Flow<PagingData<Album>>
    fun hasAnyAlbum(): Flow<Boolean>
}