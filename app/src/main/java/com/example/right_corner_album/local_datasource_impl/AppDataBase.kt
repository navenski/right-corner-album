package com.example.right_corner_album.local_datasource_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.right_corner_album.local_datasource_impl.dao.AlbumDao
import com.example.right_corner_album.local_datasource_impl.entity.AlbumEntity

@Database(version = 1,
          entities = [AlbumEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
}