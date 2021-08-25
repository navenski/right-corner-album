package com.navektest.core_common.converter

import androidx.annotation.WorkerThread

/**
 * Convert string to Md5
 */
interface Md5Converter {
    /**
     * Convert string to Md5
     * @param input string value that must be converted to md5
     * @return the md5 value.
     * When input is empty then return empty
     */
    @WorkerThread
    fun convert(input: String): String
}