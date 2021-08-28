package com.navektest.core_common.file

import java.io.File

interface FileFactory {
    fun create() : File
}