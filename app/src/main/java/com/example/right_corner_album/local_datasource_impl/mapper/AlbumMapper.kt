package com.example.right_corner_album.local_datasource_impl.mapper

import com.example.right_corner_album.local_datasource_impl.entity.AlbumEntity
import com.navektest.core_database.model.Album
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun mapTo(entity: AlbumEntity): Album {
        return Album(
            entity.id,
            entity.albumId,
            entity.title,
            entity.url,
            entity.thumbnailUrl
        )
    }

    fun mapFrom(album: Album): AlbumEntity {
        return AlbumEntity(
            album.id,
            album.albumId,
            album.title,
            album.url,
            album.thumbnailUrl
        )
    }
}