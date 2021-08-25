package com.navektest.core_database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class AlbumAndPictureEntity(@Embedded val album: AlbumEntity,
                                 @Relation(parentColumn = "id", entityColumn = "id")
                                 val picture: PictureEntity)