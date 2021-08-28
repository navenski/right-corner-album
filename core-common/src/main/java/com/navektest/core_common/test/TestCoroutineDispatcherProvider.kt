package com.navektest.core_common.test

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestCoroutineDispatcherProvider(private val coroutineDispatcher: CoroutineDispatcher) : CoroutineDispatcherProvider {
    override fun default(): CoroutineDispatcher = coroutineDispatcher

    override fun io(): CoroutineDispatcher = coroutineDispatcher

    override fun main(): CoroutineDispatcher = coroutineDispatcher
}