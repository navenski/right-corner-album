package com.example.right_corner_album.local_datasource_impl.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.example.right_corner_album.local_datasource_impl.dao.AlbumDao
import com.example.right_corner_album.local_datasource_impl.mapper.AlbumMapper
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.core_database.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumLocalDataSourceImpl @Inject constructor(coroutineDispatcherProvider: CoroutineDispatcherProvider,
                                                   private val dao: AlbumDao,
                                                   private val albumMapper: AlbumMapper) : AlbumLocalDatasource {
    private val io = coroutineDispatcherProvider.io()
    private val default = coroutineDispatcherProvider.default()

    override suspend fun get(id: Long): Album = withContext(io) {
        val entity = dao.get(id)
        albumMapper.mapTo(entity)
    }

    override suspend fun save(albums: List<Album>) = withContext(default) {
        val entities = albums.map { albumMapper.mapFrom(it) }
        dao.insert(entities)
    }

    override fun getPagedAlbums(pageSize: Int, maxSize: Int): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                maxSize = maxSize
            )
        ) {
            dao.getPagedAlbums()
        }.flow.map { pagingData ->
            pagingData.map {
                albumMapper.mapTo(it)
            }
        }
            .flowOn(default)
    }

    override fun hasAnyAlbum(): Flow<Boolean> = dao.hasAnyAlbum()
}