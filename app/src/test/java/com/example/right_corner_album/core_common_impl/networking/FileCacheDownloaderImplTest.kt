package com.example.right_corner_album.core_common_impl.networking

import com.example.right_corner_album.core_common_impl.TestBase
import com.navektest.core_common.converter.Md5Converter
import com.navektest.core_common.networking.downloder.FileDownloadData
import com.navektest.core_common.networking.downloder.FileDownloader
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class FileCacheDownloaderImplTest : TestBase() {

    private val mockFileDownloader = mock<FileDownloader>()
    private val mockMd5Converter = mock<Md5Converter>()
    private val mockFileDownloadData = mock<FileDownloadData>()

    @Test
    fun testDownloadError() = runBlocking {
        //Given setup
        whenever(mockFileDownloader.isFileAlreadyDownloaded(any())) doReturn false
        whenever(mockFileDownloader.downloadFile(any(), any())) doReturn mockFileDownloadData
        whenever(mockFileDownloadData.isSuccess) doReturn false
        whenever(mockMd5Converter.convert(any())) doReturn "md5ConvertedText"
        val downloader = FileCacheDownloaderImpl(testDispatcherProvider, mockFileDownloader, mockMd5Converter)

        //When download
        val result = downloader.tryDownload("plop.fr/007")

        //result is empty
        assertEquals("", result)
    }

    @Test
    fun testDownloadExceptionThrown() = runBlocking {
        //Given setup
        whenever(mockFileDownloader.isFileAlreadyDownloaded(any())) doReturn true
        whenever(mockFileDownloader.downloadFile(any(), any())) doReturn mockFileDownloadData
        whenever(mockFileDownloadData.isSuccess) doReturn false
        whenever(mockMd5Converter.convert(any())) doAnswer { throw Exception() }
        val downloader = FileCacheDownloaderImpl(testDispatcherProvider, mockFileDownloader, mockMd5Converter)

        //When download
        val result = downloader.tryDownload("plop.fr/007")

        //result is empty
        assertEquals("", result)
    }

    @Test
    fun testDownloadWithCache() = runBlocking {
        val expectedFilePath = "/download/005"
        //Given setup
        whenever(mockFileDownloader.isFileAlreadyDownloaded(any())) doReturn true
        whenever(mockFileDownloader.downloadFile(any(), any())) doReturn mockFileDownloadData
        whenever(mockFileDownloader.getDownloadedFilePath(any())) doReturn expectedFilePath
        whenever(mockFileDownloadData.isSuccess) doReturn true
        whenever(mockMd5Converter.convert(any())) doReturn "md5ConvertedText"
        val downloader = FileCacheDownloaderImpl(testDispatcherProvider, mockFileDownloader, mockMd5Converter)

        //When download
        val result = downloader.tryDownload("plop.fr/007")

        //result is empty
        assertEquals(expectedFilePath, result)
    }


    @Test
    fun testDownloadWithoutCache()= runBlocking {
        val expectedFilePath = "/download/005"
        //Given setup
        whenever(mockFileDownloader.isFileAlreadyDownloaded(any())) doReturn false
        whenever(mockFileDownloader.downloadFile(any(), any())) doReturn mockFileDownloadData
        whenever(mockFileDownloadData.isSuccess) doReturn true
        whenever(mockFileDownloadData.filePath) doReturn expectedFilePath
        whenever(mockMd5Converter.convert(any())) doReturn "md5ConvertedText"
        val downloader = FileCacheDownloaderImpl(testDispatcherProvider, mockFileDownloader, mockMd5Converter)

        //When download
        val result = downloader.tryDownload("plop.fr/007")

        //result is empty
        assertEquals(expectedFilePath, result)
    }
}