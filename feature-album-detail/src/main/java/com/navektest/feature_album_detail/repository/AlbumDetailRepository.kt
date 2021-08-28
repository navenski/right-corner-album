package com.navektest.feature_album_detail.repository

import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.mapper.AlbumDetailMapper
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(private val localDataSource: AlbumLocalDatasource,
                                                private val albumDetailMapper: AlbumDetailMapper) {

    suspend fun getAlbum(id: Long): AlbumDetail {
        val album = localDataSource.get(id)
        return albumDetailMapper.map(album)
    }
}