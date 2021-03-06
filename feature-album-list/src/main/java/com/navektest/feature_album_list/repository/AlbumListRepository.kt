package com.navektest.feature_album_list.repository

import com.navektest.core_common.networking.result.*
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_database.AlbumLocalDatasource
import com.navektest.feature_album_list.repository.datasource.mapper.AlbumMapper
import com.navektest.feature_album_list.repository.datasource.remote.AlbumRemoteDataSource
import com.navektest.feature_album_list.repository.datasource.remote.AlbumResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Synchronisation State with api server
 */
sealed class AlbumSyncState {
    object None : AlbumSyncState()
    object Loading : AlbumSyncState()
    object Success : AlbumSyncState()
    class Error(val exception: Exception, val isNetworkAvailable: Boolean) : AlbumSyncState()
}

/**
 * Handle Album data
 * Fetch data from remote and save it to the local database.
 * Notify
 *
 */
class AlbumListRepository(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val coroutineScope: CoroutineScope,
    private val remoteDataSource: AlbumRemoteDataSource,
    private val localDataSource: AlbumLocalDatasource,
    private val mapper: AlbumMapper
) {
    private val stateSharedFlow: MutableSharedFlow<AlbumSyncState> = MutableSharedFlow(replay = 1)

    init {
        coroutineScope.launch { stateSharedFlow.emit(AlbumSyncState.None) }
    }

    /**
     * Notify synchronisation state  with server [AlbumSyncState]
     */
    fun observeSyncState(): Flow<AlbumSyncState> = stateSharedFlow

    fun hasAnyAlbum() = localDataSource.hasAnyAlbum()

    /**
     * Fetch Albums from remote data sources
     */
    suspend fun syncWithServer() {
        stateSharedFlow.emit(AlbumSyncState.Loading)
        try {
            when (val result = remoteDataSource.getAlbums()) {
                is Success -> {
                    saveLocalDataSource(result.data)
                    stateSharedFlow.emit(AlbumSyncState.Success)
                }
                is Error -> {
                    stateSharedFlow.emit(AlbumSyncState.Error(result.exception, !result.isNetworkAvailableException()))
                }
            }
        } catch (exception: Exception) {
            stateSharedFlow.emit(AlbumSyncState.Error(exception, true))
        }
    }

    private suspend fun saveLocalDataSource(albumResponses: List<AlbumResponse>) {
        return withContext(dispatcherProvider.default()) {
            val entities = mapper.map(albumResponses)
            localDataSource.save(entities)
        }
    }

    fun getAlbums() = localDataSource.getPagedAlbums(60, 200)
}
