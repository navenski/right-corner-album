package com.navektest.feature_album_list.repository.mapper

import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.model.AlbumItem
import javax.inject.Inject

/**
 * Transform [Album] to [AlbumItem]
 */
class AlbumItemMapper @Inject constructor() {

    fun map(album: Album): AlbumItem {
        return AlbumItem(
            id = album.id,
            title = album.title,
            thumbnailsUrl = album.thumbnailUrl
        )
    }
}