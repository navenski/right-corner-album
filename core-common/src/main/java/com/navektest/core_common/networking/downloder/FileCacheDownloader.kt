package com.navektest.core_common.networking.downloder

/**
 * Provide file absolutePath
 * Download file and save it to the local storage.
 * If file already exist on the local storage, download will be skipped.
 */
interface FileCacheDownloader {
    /**
     * Download file into storage.
     * Dispatcher used: default
     * If file already exist in storage. It will return the path
     * @return the storage absolute path of the downloaded file
     */
    suspend fun tryDownload(thumbnailUrl: String): String
}