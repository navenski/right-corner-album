package com.example.right_corner_album.core_common_impl

import com.navektest.core_common.test.TestCoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

/**
 * Unit test base class
 */
abstract class TestBase {
    protected val testDispatcher = TestCoroutineDispatcher()
    protected val testDispatcherProvider = TestCoroutineDispatcherProvider(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}