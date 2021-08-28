package com.navektest.core_common.file

import java.io.File

/**
 * Handle File and Directory operations
 */
interface FileStorage {
    /**
     * Get or create a file from the internal storagee
     * @param filename the file name to retrieve or create
     * @param directory Name of the directory to retrieve.This is a directory
     * that is created as part of your application data.
     */
    fun getFile(directory: String, filename: String): File
}