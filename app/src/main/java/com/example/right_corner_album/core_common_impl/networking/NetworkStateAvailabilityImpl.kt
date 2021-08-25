package com.example.right_corner_album.core_common_impl.networking

import android.content.Context
import android.net.ConnectivityManager
import com.navektest.core_common.networking.NetworkStateAvailability

class NetworkStateAvailabilityImpl(private val context: Context) : NetworkStateAvailability {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}