package com.example.right_corner_album.local_datasource_impl.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(@PrimaryKey val id: Long,
                       @ColumnInfo(name="albumId") val albumId : Long,
                       @ColumnInfo(name = "title") val title: String,
                       @ColumnInfo(name = "url") val url: String,
                       @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String)