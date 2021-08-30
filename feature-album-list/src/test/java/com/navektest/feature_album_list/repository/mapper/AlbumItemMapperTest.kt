package com.navektest.feature_album_list.repository.mapper

import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.model.AlbumItem
import org.junit.Assert
import org.junit.Test

class AlbumItemMapperTest {


    @Test
    fun testMap() {
        //Given setup
        val album = Album(1L, 200L, "le monde", "plop.fr", "plop.thumbnail.fr")
        val expectedResult = AlbumItem(1L, "le monde", "plop.thumbnail.fr")
        val mapper = AlbumItemMapper()

        //When map
        val result = mapper.map(album)

        //Then
        Assert.assertEquals(expectedResult, result)
    }
}