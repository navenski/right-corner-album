package com.navektest.core_database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.navektest.core_database.entities.AlbumAndPictureEntity

@Dao
interface AlbumAndPictureDao {
    @Query("SELECT * FROM album_table ORDER BY id")
    fun getPagedAlbums(): PagingSource<Int, AlbumAndPictureEntity>
}