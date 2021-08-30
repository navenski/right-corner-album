package com.navektest.feature_album_detail.repository.mapper

import com.navektest.core_database.model.Album
import com.navektest.feature_album_detail.model.AlbumDetail
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class AlbumDetailMapperTest{

    @Test
    fun testMap() {
        //Given
        val album = Album(200, 10, "the album", "url.com/10", "thumb.url.com/10")
        val expected = AlbumDetail(200, "the album", "thumb.url.com/10","url.com/10")
        val mapper = AlbumDetailMapper()
        //When
        val result = mapper.map(album)

        //Then
        Assert.assertEquals(expected, result)
    }
}