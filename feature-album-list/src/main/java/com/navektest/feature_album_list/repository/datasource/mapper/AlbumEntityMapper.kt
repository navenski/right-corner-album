package com.navektest.feature_album_list.repository.datasource.mapper

import androidx.annotation.WorkerThread
import com.navektest.core_database.entities.AlbumEntity
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import javax.inject.Inject

class AlbumEntityMapper @Inject constructor() {

    @WorkerThread
    fun map(albumResponses: List<AlbumResponse>): List<AlbumEntity> {
        return albumResponses.map { toEntity(it) }
            .toList()
    }

    private fun toEntity(albumResponse: AlbumResponse): AlbumEntity {
        return AlbumEntity(
            albumResponse.id, albumResponse.albumId, albumResponse.title, albumResponse.url, albumResponse.thumbnailUrl
        )
    }
}