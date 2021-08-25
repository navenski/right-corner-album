package com.navektest.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.navektest.core_database.daos.AlbumDao
import com.navektest.core_database.entities.AlbumEntity

@Database(version = 1,
          entities = [AlbumEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
}