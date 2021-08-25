package com.navektest.core_database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.navektest.core_database.entities.PictureEntity

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<PictureEntity>)

    @Delete()
    suspend fun delete(entities: List<PictureEntity>)

    @Delete()
    suspend fun delete(entity: PictureEntity)

    @Query("SELECT CAST(COUNT(*) AS BIT) FROM picture_table WHERE id = :id")
    suspend fun exists(id: Long): Boolean

    @Query("SELECT * FROM picture_table WHERE id = :id")
    suspend fun get(id: Long): PictureEntity
}