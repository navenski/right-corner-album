package com.navektest.feature_album_detail.repository

import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.core_database.model.Album
import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.mapper.AlbumDetailMapper
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(private val localDataSource: AlbumLocalDatasource) {

    suspend fun getAlbum(id: Long): Album = localDataSource.get(id)

}