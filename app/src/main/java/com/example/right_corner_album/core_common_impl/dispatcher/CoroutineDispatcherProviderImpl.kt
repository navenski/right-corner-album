package com.example.right_corner_album.core_common_impl.dispatcher

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProviderImpl @Inject constructor(): CoroutineDispatcherProvider {
    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main
}