package com.example.right_corner_album.core_common_impl.networking

import android.content.Context
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.downloder.FileDownloadData
import com.navektest.core_common.networking.downloder.FileDownloader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.BufferedSink
import okio.buffer
import okio.sink
import java.io.File
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FileStorageDirectoryName

class FileDownloaderImpl @Inject constructor(@ApplicationContext private val context: Context,
                                             private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
                                             private val bufferedSourceFileWriter: BufferedSourceFileWriter,
                                             private val okHttpClient: OkHttpClient,
                                             @FileStorageDirectoryName private val directoryName: String) : FileDownloader {
    override suspend fun downloadFile(url: String, filename: String): FileDownloadData {
        return withContext(coroutineDispatcherProvider.io()) {
            val request = Request.Builder()
                .url(url)
                .build()

            try {
                val response =
                    okHttpClient.newCall(request)
                        .execute()

                val downloadedFile = getFile(filename)
                val success = bufferedSourceFileWriter.write(response.body!!.source(), downloadedFile)
                response.close()
                FileDownloadData(success, if (success)downloadedFile.absolutePath else "")
            } catch (exception: Exception) {
                FileDownloadData(false, "")
            }
        }
    }

    override suspend fun isFileAlreadyDownloaded(fileName: String): Boolean =
        withContext(coroutineDispatcherProvider.io()) { getFile(fileName).exists() }

    override suspend fun getFileDownloadedPath(fileName: String): String = withContext(coroutineDispatcherProvider.io()) {
        getFile(fileName).absolutePath
    }

    private fun getFile(filename: String): File {
        val fileDir = context.getDir(directoryName, Context.MODE_PRIVATE)
        return File(fileDir, filename)
    }
}