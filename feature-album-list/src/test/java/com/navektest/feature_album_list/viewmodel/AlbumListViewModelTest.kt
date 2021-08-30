package com.navektest.feature_album_list.viewmodel

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.map
import androidx.recyclerview.widget.ListUpdateCallback
import com.navektest.core_common.networking.NetworkAvailabilityException
import com.navektest.core_common.test.TestCoroutineDispatcherProvider
import com.navektest.core_database.model.Album
import com.navektest.feature_album_list.model.AlbumItem
import com.navektest.feature_album_list.repository.AlbumListRepository
import com.navektest.feature_album_list.repository.AlbumListRepositoryFactory
import com.navektest.feature_album_list.repository.AlbumSyncState
import com.navektest.feature_album_list.repository.mapper.AlbumItemMapper
import com.navektest.feature_album_list.router.AlbumListRouter
import com.navektest.feature_album_list.view.adapter.AlbumAdapter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AlbumListViewModelTest {
    private val mockFactory = mock<AlbumListRepositoryFactory>()
    private val mockRepo = mock<AlbumListRepository>()
    private val mockMapper = mock<AlbumItemMapper>()
    private val mockRouter = mock<AlbumListRouter>()
    private val mockAlbumItem = mock<AlbumItem>()
    private val testDispatcher = TestCoroutineDispatcher()
    private val albumItem = AlbumItem(1, "title", "plp.com")
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
    fun testDetailNavigation() {
        val id = 50L
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockAlbumItem.id) doReturn id
        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper)
        viewModel.bindRouter(mockRouter)
        viewModel.initialize()
        //When
        viewModel.navigateToDetails(mockAlbumItem)
        //Then
        verify(mockRouter, times(1)).navigateToDetails(eq(id))
    }

    @Test
    fun testRefresh() = runBlocking {
        val syncState = AlbumSyncState.Loading
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(AlbumSyncState.Loading)
        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        viewModel.initialize()
        clearInvocations(mockRepo)
        //When
        viewModel.refresh()
        //Then
        verify(mockRepo, times(1)).syncWithServer()
    }

    @Test
    fun testAlbumSyncStateStateLoading() {
        val syncState = AlbumSyncState.Loading
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(AlbumSyncState.Loading)
        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper)
        //When
        viewModel.initialize()
        //Then
        Assert.assertTrue(viewModel.isLoading.get())
        verify(mockRouter, never()).displaySnackBarSuccess()
        verify(mockRouter, never()).displaySnackBarError(any())
    }

    @Test
    fun testAlbumSyncStateStateSuccess() = runBlocking {
        val syncState = AlbumSyncState.Success
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        viewModel.initialize()
        //Then
        Assert.assertFalse(viewModel.isLoading.get())

        verify(mockRouter, times(1)).displaySnackBarSuccess()
        verify(mockRouter, never()).displaySnackBarError(any())
    }

    @Test
    fun testAlbumSyncStateStateError() {
        val syncState = AlbumSyncState.Error(Exception(), true)
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        viewModel.initialize()
        //Then
        Assert.assertFalse(viewModel.isLoading.get())

        verify(mockRouter, times(1)).displaySnackBarError(false)
        verify(mockRouter, never()).displaySnackBarSuccess()
    }

    @Test
    fun testAlbumSyncStateStateNone() {
        val syncState = AlbumSyncState.None
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        viewModel.initialize()
        //Then
        Assert.assertFalse(viewModel.isLoading.get())

        verify(mockRouter, never()).displaySnackBarSuccess()
        verify(mockRouter, never()).displaySnackBarError(any())
    }

    @Test
    fun testHasNoAlbumTrue() {
        val syncState = AlbumSyncState.None
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(false)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        viewModel.initialize()
        //Then
        Assert.assertTrue(viewModel.hasNoAlbums.get())
    }

    @Test
    fun testHasNoAlbumFalse() {
        val syncState = AlbumSyncState.None
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(true)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        viewModel.initialize()
        //Then
        Assert.assertFalse(viewModel.hasNoAlbums.get())
    }

    @Test
    fun testAlbums() = runBlockingTest(testDispatcher) {
        val pagingSource = MockPagingSource()
        val mockPagingData = pagingSource.getPagingData()
        val syncState = AlbumSyncState.None
        //Given setup
        whenever(mockFactory.create(any())) doReturn mockRepo
        whenever(mockRepo.hasAnyAlbum()) doReturn flowOf(true)
        whenever(mockRepo.observeSyncState()) doReturn flowOf(syncState)
        whenever(mockRepo.getAlbums()) doReturn mockPagingData
        whenever(mockMapper.map(any())) doReturn albumItem

        val differ = AsyncPagingDataDiffer(
            diffCallback = AlbumAdapter.diffCallback,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = testDispatcher,
            workerDispatcher = testDispatcher,
        )

        val viewModel = AlbumListViewModel(mockFactory, testDispatcherProvider, mockMapper).apply { bindRouter(mockRouter) }
        //When
        val job = launch {
            viewModel.albums.collectLatest { pagingData ->
                differ.submitData(pagingData)
            }

        }

        //Then
        Assert.assertEquals(listOf(albumItem), differ.snapshot().items)

        job.cancel()
    }

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
    class MockPagingSource() {

        val albums = listOf(mock<Album>())

        fun getPagingData() = Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                maxSize = 200
            )
        ) {
            getPaging()
        }.flow

        private fun getPaging(): PagingSource<Int, Album> {
            return object : PagingSource<Int, Album>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
                    return LoadResult.Page(
                        data = albums,
                        prevKey = null,
                        nextKey = null,
                    )
                }

                override fun getRefreshKey(state: PagingState<Int, Album>): Int? = null
            }
        }
    }

}
