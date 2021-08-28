package com.navektest.core_common.networking.result

import com.navektest.core_common.networking.NetworkAvailabilityException

/**
 * Data Holder for NetworkResult
 */
sealed class NetworkResult<T> {
    companion object {
        fun <T> error(exception: Exception) = Error<T>(exception)
        fun <T> success(data: T) = Success<T>(data)
    }
}

data class Success<T>(val data: T) : NetworkResult<T>()
data class Error<T>(val exception: Exception) : NetworkResult<T>() {
    /**
     * check if it's a network reachability error
     */
    fun isNetworkAvailableException() = exception is NetworkAvailabilityException
}