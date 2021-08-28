package com.navektest.feature_album_list.repository.datasource.mapper

import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import org.junit.Assert
import org.junit.Test

class AlbumMapperTest {

    @Test
    fun testMap() {
        val response = AlbumResponse(0, 0, "hello", "domain.url", "thubmnail")
        val response2 = AlbumResponse(2, 1, "hello1", "domain.url1", "thubmnail1")
        val mapper = AlbumMapper()
        val expectedResult =
            listOf(Album(0, 0, "hello", "domain.url", "thubmnail"), Album(1, 2, "hello1", "domain.url1", "thubmnail1"))
        val result = mapper.map(listOf(response, response2))

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun testEmptyMap() {
        val mapper = AlbumMapper()
        val result = mapper.map(listOf())
        Assert.assertEquals(0, result.count())
    }
}