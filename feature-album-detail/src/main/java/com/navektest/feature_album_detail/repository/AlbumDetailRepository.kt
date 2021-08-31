package com.navektest.feature_album_detail.repository

import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.core_database.model.Album
import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.mapper.AlbumDetailMapper
import java.lang.Exception
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(private val localDataSource: AlbumLocalDatasource) {

    /**
     * Get Album from database
     * If an error occured, it'll return null
     */
    suspend fun getAlbum(id: Long): Album? {
        return try {
            localDataSource.get(id)
        } catch (exception: Exception) {
            null
        }
    }
}