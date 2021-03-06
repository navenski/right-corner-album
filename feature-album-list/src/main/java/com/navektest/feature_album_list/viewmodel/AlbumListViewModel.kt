package com.navektest.feature_album_list.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.feature_album_list.model.AlbumItem
import com.navektest.feature_album_list.router.AlbumListRouter
import com.navektest.feature_album_list.repository.AlbumListRepositoryFactory
import com.navektest.feature_album_list.repository.AlbumSyncState
import com.navektest.feature_album_list.repository.mapper.AlbumItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(albumListRepositoryFactory: AlbumListRepositoryFactory,
                                             dispatcherProvider: CoroutineDispatcherProvider,
                                             private val itemMapper: AlbumItemMapper) : ViewModel() {
    private var routerWeakRef = WeakReference<AlbumListRouter>(null)
    private val repository = albumListRepositoryFactory.create(viewModelScope)
    private var isInitialized = false
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val hasNoAlbums: ObservableBoolean = ObservableBoolean(false)

    val albums =
        repository.getAlbums()
            .catch { Timber.e("getAlbums from repository error", it) }
            .map { pagingData ->
                pagingData.map {
                    itemMapper.map(it)
                }
            }
            .distinctUntilChanged()
            .flowOn(dispatcherProvider.default())
            .cachedIn(viewModelScope)

    /**
     * Initialize the viewmodel calls
     */
    fun initialize() {
        if (isInitialized)
            return

        isInitialized = true
        observeSyncStatus()
        viewModelScope.launch {
            repository.syncWithServer()
            repository.hasAnyAlbum()
                .catch { Timber.w("error when observing hasAnyAlbum") }
                .collect { hasNoAlbums.set(!it) }
        }
    }

    private fun observeSyncStatus() = viewModelScope.launch {
        repository.observeSyncState()
            .catch {
                isLoading.set(false)
                routerWeakRef.get()
                    ?.displaySnackBarError(false)
            }
            .collect {
                when (it) {
                    is AlbumSyncState.Loading -> {
                        isLoading.set(true)
                    }
                    is AlbumSyncState.Success -> {
                        isLoading.set(false)
                        routerWeakRef.get()
                            ?.displaySnackBarSuccess()
                    }
                    is AlbumSyncState.Error -> {
                        isLoading.set(false)
                        routerWeakRef.get()
                            ?.displaySnackBarError(!it.isNetworkAvailable)
                    }

                    is AlbumSyncState.None -> {
                        isLoading.set(false)
                    }
                }
            }
    }

    fun navigateToDetails(albumItem: AlbumItem) = routerWeakRef.get()
        ?.navigateToDetails(albumItem.id)

    fun bindRouter(router: AlbumListRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun refresh() = viewModelScope.launch { repository.syncWithServer() }

}