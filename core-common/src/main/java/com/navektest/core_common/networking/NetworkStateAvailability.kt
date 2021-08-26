package com.navektest.core_common.networking

/**
 * Get the availability of the network state.
 **** Check if the user has internet connection or not ***
 */
interface NetworkStateAvailability {
    /**
     * Check if network is available
     */
    fun isNetworkAvailable() : Boolean
}