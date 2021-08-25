package com.navektest.feature_album_detail.repository.mapper

import com.navektest.core_database.entities.AlbumEntity
import com.navektest.feature_album_detail.model.AlbumDetail
import javax.inject.Inject

class AlbumDetailMapper @Inject constructor() {

    fun map(albumEntity: AlbumEntity): AlbumDetail {
        return AlbumDetail(
            albumEntity.id,
            albumEntity.title,
            albumEntity.thumbnailUrl,
            albumEntity.url
        )
    }

}