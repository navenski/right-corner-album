package com.navektest.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.navektest.core_database.daos.AlbumAndPictureDao
import com.navektest.core_database.daos.AlbumDao
import com.navektest.core_database.daos.PictureDao
import com.navektest.core_database.entities.AlbumEntity
import com.navektest.core_database.entities.PictureEntity

@Database(version = 1,
          entities = [AlbumEntity::class, PictureEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun pictureDao() : PictureDao
    abstract fun albumAndPictureDao(): AlbumAndPictureDao
}