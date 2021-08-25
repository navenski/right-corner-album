package com.navektest.feature_album_detail.repository

import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.localdatasource.AlbumDetailLocalDataSource
import com.navektest.feature_album_detail.repository.mapper.AlbumDetailMapper
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(private val localDataSource: AlbumDetailLocalDataSource,
                                                private val albumDetailMapper: AlbumDetailMapper) {

    suspend fun getAlbum(id: Long): AlbumDetail  {
        val entity = localDataSource.getAlbum(id)
        return albumDetailMapper.map(entity)
    }
}