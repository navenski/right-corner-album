package com.navektest.core_common.networking.downloder

/**
 * @param isSuccess : has the file download succeed.
 * @param filePath
 */
data class FileDownloadData(val isSuccess: Boolean, val filePath:String)

/**
 * File downloader class.
 * Download file and store it in the internal storage directory
 */
interface FileDownloader {
    /**
     * @param url file to download
     * @param fileName
     * @return [FileDownloadData]
     */
    suspend fun downloadFile(url: String, fileName: String) : FileDownloadData

    /**
     * Check if the file already stored on the internal storage
     */
    suspend fun isFileAlreadyDownloaded(fileName: String): Boolean

    /**
     * Get the path location of the file
     */
    suspend fun getFileDownloadedPath(fileName: String): String
}