package com.navektest.core_database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.navektest.core_database.entities.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    //First part insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<AlbumEntity>)

    @Query("SELECT * FROM album_table where id = :id")
    suspend fun get(id: Long): AlbumEntity

    @Query("SELECT * FROM album_table")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM album_table")
    fun getPagedAlbums(): PagingSource<Int, AlbumEntity>

    @Query("SELECT CAST(COUNT(*) AS BIT) FROM album_table")
    fun hasAnyAlbum(): Flow<Boolean>
}