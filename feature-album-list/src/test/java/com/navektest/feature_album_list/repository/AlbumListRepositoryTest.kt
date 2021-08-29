package com.navektest.feature_album_list.repository

import com.navektest.core_common.networking.result.Error
import com.navektest.core_common.networking.result.Success
import com.navektest.core_common.test.TestCoroutineDispatcherProvider
import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.repository.datasource.mapper.AlbumMapper
import com.navektest.feature_album_list.repository.datasource.remote.AlbumRemoteDataSource
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testDispatcherProvider = TestCoroutineDispatcherProvider(testDispatcher)

    private val mockRemoteDataSource = mock<AlbumRemoteDataSource>()
    private val mockLocalDataSource = mock<AlbumLocalDatasource>()
    private val mockEntityMapper = mock<AlbumMapper>()
    private val mockSuccesResponse = mock<Success<List<AlbumResponse>>>()
    private val mockErrorResponse = mock<Error<List<AlbumResponse>>>()
    private val mockData = mock<List<AlbumResponse>>()
    private val mockAlbum = mock<List<Album>>()

    fun createRepo(coroutineScope: CoroutineScope) = AlbumListRepository(testDispatcherProvider,
                                                                         coroutineScope,
                                                                         mockRemoteDataSource,
                                                                         mockLocalDataSource,
                                                                         mockEntityMapper)

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

        //Given remote  request succeed
        whenever(mockRemoteDataSource.getAlbums()) doReturn mockSuccesResponse
        whenever(mockSuccesResponse.data) doReturn mockData
        whenever(mockEntityMapper.map(any())) doReturn mockAlbum

        val repository = createRepo(this)

        //When syncWithServer
        val firstState =
            repository.observeSyncState()
                .first()
        repository.syncWithServer()

        //Then ensure data flow correct
        assertEquals(AlbumSyncState.None, firstState)
        val state = repository.observeSyncState()
            .first()
        assertEquals(AlbumSyncState.Success, state)
        //Then data store in local data source
        verify(mockLocalDataSource, times(1)).save(mockAlbum)
        verify(mockEntityMapper, times(1)).map(mockData)
    }

    @Test
    fun testSyncWithServerError() = runBlockingTest(testDispatcher) {

        //Given remote  request succeed
        whenever(mockRemoteDataSource.getAlbums()) doReturn mockErrorResponse
        whenever(mockSuccesResponse.data) doReturn mockData

        val repository = createRepo(this)

        //When syncWithServer
        repository.syncWithServer()

        val state = repository.observeSyncState()
            .first()

        //Then
        assertTrue(state is AlbumSyncState.Error)
        verify(mockLocalDataSource, never()).save(any())
        verify(mockEntityMapper, never()).map(any())
    }

    @Test
    fun testGetAlbums() = runBlockingTest(testDispatcher) {
        val repository = createRepo(this)
        //When
        repository.getAlbums()
        verify(mockLocalDataSource, times(1)).getPagedAlbums(any(), any())
    }
}