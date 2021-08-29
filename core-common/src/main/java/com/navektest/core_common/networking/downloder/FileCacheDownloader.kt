package com.navektest.core_common.networking.downloder

/**
 * Provide file absolutePath
 * Download file and save it to the local storage.
 * If file already exist on the local storage, download will be skipped , and local file path will be returned
 */
interface FileCacheDownloader {
    /**
     * Download file into storage.
     * Dispatcher used: default
     * If file already exist in storage. It will return the path
     * @return
     * - ABSOLUTE FILEPATH of the downloaded file. EMPTY if an error occurred
     * - EMPTY if an error occurred
     */
    suspend fun tryDownload(thumbnailUrl: String): String
}