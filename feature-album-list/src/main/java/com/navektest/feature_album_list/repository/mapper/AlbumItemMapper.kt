package com.navektest.feature_album_list.repository.mapper

import androidx.annotation.WorkerThread
import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.model.AlbumItem
import javax.inject.Inject

class AlbumItemMapper @Inject constructor() {
    @WorkerThread
    fun map(album: Album): AlbumItem {
        return AlbumItem(
            id = album.id,
            title = album.title,
            thumbnailsUrl = album.thumbnailUrl
        )
    }
}