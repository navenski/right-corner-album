package com.navektest.core_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_table")
data class PictureEntity(@PrimaryKey val id: Long,
                         @ColumnInfo(name = "thumbnailPath") val thumbnailPath: String,
                         @ColumnInfo(name = "picturePath") val picturePath: String)