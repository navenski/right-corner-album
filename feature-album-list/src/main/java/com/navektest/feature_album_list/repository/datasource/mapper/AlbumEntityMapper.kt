package com.navektest.feature_album_list.repository.datasource.mapper

import androidx.annotation.WorkerThread
import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import javax.inject.Inject

class AlbumEntityMapper @Inject constructor() {

    @WorkerThread
    fun map(albumResponses: List<AlbumResponse>): List<Album> {
        return albumResponses.map { toAlbum(it) }
            .toList()
    }

    private fun toAlbum(albumResponse: AlbumResponse): Album {
        return Album(
            albumResponse.id, albumResponse.albumId, albumResponse.title, albumResponse.url, albumResponse.thumbnailUrl
        )
    }
}