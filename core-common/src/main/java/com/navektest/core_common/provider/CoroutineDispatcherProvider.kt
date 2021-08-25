package com.navektest.core_common.provider

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Provide [CoroutineDispatcher]
 * default
 * io
 * main
 */
interface CoroutineDispatcherProvider {
    fun default() : CoroutineDispatcher
    fun io() : CoroutineDispatcher
    fun main() : CoroutineDispatcher
}