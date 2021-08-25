package com.navektest.feature_album_list.repository.mapper

import androidx.annotation.WorkerThread
import com.navektest.core_database.entities.AlbumEntity
import com.navektest.feature_album_list.model.AlbumItem

class AlbumItemMapper {
    @WorkerThread
    fun map(entity: AlbumEntity): AlbumItem {
        return AlbumItem(
            id = entity.id,
            title = entity.title,
            thumbnailsUrl = entity.thumbnailUrl
        )
    }
}