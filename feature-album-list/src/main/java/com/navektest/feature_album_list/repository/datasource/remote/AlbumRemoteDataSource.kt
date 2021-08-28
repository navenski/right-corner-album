package com.navektest.feature_album_list.repository.datasource.remote

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.NetworkAvailabilityException
import com.navektest.core_common.networking.NetworkStateAvailability
import com.navektest.core_common.networking.result.NetworkResult
import com.navektest.core_common.networking.result.Success
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Fetch data from album api server.
 *  return [Success] server status response 200
 *  else return [Error]
 */
class AlbumRemoteDataSource @Inject constructor(private val dispatcherProvider: CoroutineDispatcherProvider,
                            private val albumApi: AlbumApi,
                            private val networkStateAvailability: NetworkStateAvailability) {

    suspend fun getAlbums(): NetworkResult<List<AlbumResponse>> = withContext(dispatcherProvider.io()) {
        try {
            val albums = albumApi.getAlbums()
            NetworkResult.success(albums)
        } catch (exception: Exception) {
            if (networkStateAvailability.isNetworkAvailable())
                NetworkResult.error(exception)
            else
                NetworkResult.error(NetworkAvailabilityException("not connected"))
        }
    }
}