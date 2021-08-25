package com.example.right_corner_album.core_common_impl.provider

import com.navektest.core_common.converter.Md5Converter
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.downloder.FileDownloader
import com.navektest.core_common.provider.FilePathProvider
import kotlinx.coroutines.withContext

class FilePathProviderImpl(private val dispatcherProvider: CoroutineDispatcherProvider,
                           private val fileDownloader: FileDownloader,
                           private val md5Converter: Md5Converter) : FilePathProvider {
    override suspend fun provide(thumbnailUrl: String) = withContext(dispatcherProvider.default()) {
        try {
            val md5FileName = md5Converter.convert(thumbnailUrl)

            if (fileDownloader.isFileAlreadyDownloaded(md5FileName)) {
                return@withContext fileDownloader.getFileDownloadedPath(md5FileName)
            }

            val fileData = fileDownloader.downloadFile(thumbnailUrl, md5FileName)
            if (fileData.isSuccess) {
                fileData.filePath
            } else
                EMPTY
        } catch (exception: Exception) {
            EMPTY
        }
    }

    companion object {
        const val EMPTY = ""
    }
}