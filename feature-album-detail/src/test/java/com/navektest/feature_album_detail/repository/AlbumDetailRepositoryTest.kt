package com.navektest.feature_album_detail.repository

import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.core_database.model.Album
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class AlbumDetailRepositoryTest{

    @Test
    fun testGetAlbum() {
        runBlocking {
            //Given
            val mockDataSource = mock<AlbumLocalDatasource>()
            val album = Album(1L, 2L, "plop", "plop.fr/012", "anana.fr/45")
            whenever(mockDataSource.get(any())) doReturn album
            val repository = AlbumDetailRepository(mockDataSource)
            //When
            val result = repository.getAlbum(20L)
            //Then
            Assert.assertEquals(album, result)
            verify(mockDataSource, times(1)).get(eq(20L))
        }
    }

    @Test
    fun testException(){
        runBlocking {
            //Given
            val mockDataSource = mock<AlbumLocalDatasource>()
            val album = Album(1L, 2L, "plop", "plop.fr/012", "anana.fr/45")
            whenever(mockDataSource.get(any())) doAnswer { throw Exception() }
            val repository = AlbumDetailRepository(mockDataSource)
            //When
            val result = repository.getAlbum(20L)
            //Then
            Assert.assertEquals(album, result)
            verify(mockDataSource, times(1)).get(eq(20L))
        }
    }
}