package com.navektest.core_common.networking.downloder

/**
 * @param isSuccess : has the file download succeed.
 * @param filePath
 */
data class FileDownloadData(val isSuccess: Boolean, val filePath:String)

/**
 * File downloader class.
 * Download file and store it in the internal storage directory
 * If the file already exist on the internal storage, it will be replaced
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
     * Get the path location of the already downloaded file
     */
    suspend fun getDownloadedFilePath(fileName: String): String
}