package com.navektest.feature_album_list.repository.mapper

import androidx.annotation.WorkerThread
import com.navektest.core_database.entities.AlbumEntity
import com.navektest.feature_album_list.model.AlbumItem
import javax.inject.Inject

class AlbumItemMapper @Inject constructor() {
    @WorkerThread
    fun map(entity: AlbumEntity): AlbumItem {
        return AlbumItem(
            id = entity.id,
            title = entity.title,
            thumbnailsUrl = entity.thumbnailUrl
        )
    }
}