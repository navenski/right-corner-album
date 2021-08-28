package com.navektest.feature_album_detail.repository.mapper

import com.navektest.core_database.model.Album
import com.navektest.feature_album_detail.model.AlbumDetail
import javax.inject.Inject

class AlbumDetailMapper @Inject constructor() {

    fun map(album: Album): AlbumDetail {
        return AlbumDetail(
            album.id,
            album.title,
            album.thumbnailUrl,
            album.url
        )
    }

}