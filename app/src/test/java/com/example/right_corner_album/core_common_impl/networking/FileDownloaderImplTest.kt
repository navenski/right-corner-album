package com.example.right_corner_album.core_common_impl.networking

import android.content.Context
import com.navektest.core_common.test.TestCoroutineDispatcherProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okio.BufferedSource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
@RunWith(RobolectricTestRunner::class)
class FileDownloaderImplTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testDispatcherProvider = TestCoroutineDispatcherProvider(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val mockBufferedSourceFileWriter = mock<BufferedSourceFileWriter>()
    private val mockOkHttpClient = mock<OkHttpClient>()
    private val mockCall = mock<Call>()
    private val mockResponse = mock<Response>()
    private val mockBody = mock<ResponseBody>()
    private val mockBufferedSource = mock<BufferedSource>()

    @Test
    fun testDownloadFileSuccess() = runBlocking {
        //Given setup
        val directoryName = "download"
        whenever(mockOkHttpClient.newCall(any())) doReturn mockCall
        whenever(mockCall.execute()) doReturn mockResponse
        whenever(mockResponse.body) doReturn mockBody
        whenever(mockBody.source()) doReturn mockBufferedSource
        whenever(mockBufferedSourceFileWriter.write(any(), any())) doReturn true

        val downloader =
            FileDownloaderImpl(mockContext,
                               testDispatcherProvider,
                               mockBufferedSourceFileWriter,
                               mockOkHttpClient,
                               directoryName)
        //When
        val result = downloader.downloadFile("http://plop.com/01232", "blob2")

        assertTrue(result.isSuccess)
        assertTrue(result.filePath.endsWith("blob2"))

    }

    @Test
    fun testDownloadFileErrorFileWriting() = runBlocking {
        //Given setup
        val directoryName = "download"
        whenever(mockOkHttpClient.newCall(any())) doReturn mockCall
        whenever(mockCall.execute()) doReturn mockResponse
        whenever(mockResponse.body) doReturn mockBody
        whenever(mockBody.source()) doReturn mockBufferedSource
        whenever(mockBufferedSourceFileWriter.write(any(), any())) doReturn false

        val downloader =
            FileDownloaderImpl(mockContext,
                               testDispatcherProvider,
                               mockBufferedSourceFileWriter,
                               mockOkHttpClient,
                               directoryName)
        //When
        val result = downloader.downloadFile("http://plop.com/01232", "blob2")

        assertFalse(result.isSuccess)
        assertEquals("", result.filePath)
    }

    @Test
    fun testDownloadFileErrorException() = runBlocking {
        //Given setup
        val directoryName = "download"
        whenever(mockOkHttpClient.newCall(any())) doReturn mockCall
        whenever(mockCall.execute()) doReturn mockResponse
        whenever(mockResponse.body) doReturn null
        whenever(mockBody.source()) doReturn mockBufferedSource
        whenever(mockBufferedSourceFileWriter.write(any(), any())) doReturn true

        val downloader =
            FileDownloaderImpl(mockContext,
                               testDispatcherProvider,
                               mockBufferedSourceFileWriter,
                               mockOkHttpClient,
                               directoryName)
        //When
        val result = downloader.downloadFile("http://plop.com/01232", "blob2")

        assertFalse(result.isSuccess)
        assertEquals("", result.filePath)

    }
}