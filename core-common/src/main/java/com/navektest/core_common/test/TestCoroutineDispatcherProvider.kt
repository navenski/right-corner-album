package com.navektest.core_common.test

import com.navektest.core_common.provider.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Test [CoroutineDispatcherProvider] implementation
 */
class TestCoroutineDispatcherProvider(private val coroutineDispatcher: CoroutineDispatcher) : CoroutineDispatcherProvider {
    override fun default(): CoroutineDispatcher = coroutineDispatcher

    override fun io(): CoroutineDispatcher = coroutineDispatcher

    override fun main(): CoroutineDispatcher = coroutineDispatcher
}