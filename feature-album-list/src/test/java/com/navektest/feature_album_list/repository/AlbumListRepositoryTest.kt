package com.navektest.feature_album_list.repository

import com.navektest.core_common.networking.result.Success
import com.navektest.core_common.test.TestCoroutineDispatcherProvider
import com.navektest.feature_album_list.repository.datasource.local.AlbumListLocalDataSource
import com.navektest.feature_album_list.repository.datasource.mapper.AlbumEntityMapper
import com.navektest.feature_album_list.repository.datasource.remote.AlbumRemoteDataSource
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testDispatcherProvider = TestCoroutineDispatcherProvider(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSyncWithServerSuccess() = runBlockingTest(testDispatcher) {
        val mockRemoteDataSource = mock<AlbumRemoteDataSource>()
        val mockLocalDataSource = mock<AlbumListLocalDataSource>()
        val mockEntityMapper = mock<AlbumEntityMapper>()
        val mockSuccesResponse = mock<Success<List<AlbumResponse>>>()
        val mockData = mock<List<AlbumResponse>>()
        //Given remote  request succeed
        whenever(mockRemoteDataSource.getAlbums()) doReturn mockSuccesResponse
        whenever(mockSuccesResponse.data) doReturn mockData
        val repository =
            AlbumListRepository(testDispatcherProvider, this, mockRemoteDataSource, mockLocalDataSource, mockEntityMapper)

        //When syncWithServer
        repository.syncWithServer()

        //Then ensure data flow correct
        val firstState = repository.observeSyncState().first()
        assertEquals(AlbumSyncState.None, firstState)
        repository.syncWithServer()
    }

    fun testSyncWithServerError() {
    }
}