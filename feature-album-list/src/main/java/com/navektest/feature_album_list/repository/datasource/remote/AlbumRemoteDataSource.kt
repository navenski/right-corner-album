package com.navektest.feature_album_list.repository.datasource.remote

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.NetworkAvailabilityException
import com.navektest.core_common.networking.NetworkStateAvailability
import kotlinx.coroutines.withContext

sealed class NetworkResult<T>

data class Success<T>(val data: T) : NetworkResult<T>()
data class Error<T>(val exception: Exception) : NetworkResult<T>() {
    /**
     * check if it's a network reachability error
     */
    fun isNetworkAvailableException() = exception is NetworkAvailabilityException
}

/**
 * Fetch data from album api server.
 *  return [Success] server status response 200
 *  else return [Error]
 */
class AlbumRemoteDataSource(private val dispatcherProvider: CoroutineDispatcherProvider,
                            private val albumApi: AlbumApi,
                            private val networkStateAvailability: NetworkStateAvailability) {

    suspend fun getAlbums(): NetworkResult<List<AlbumResponse>> = withContext(dispatcherProvider.io()) {
        try {
            val albums = albumApi.getAlbums()
            Success(albums)
        } catch (exception: Exception) {
            if (networkStateAvailability.isNetworkAvailable())
                Error(exception)
            else
                Error(NetworkAvailabilityException("not connected"))
        }
    }
}