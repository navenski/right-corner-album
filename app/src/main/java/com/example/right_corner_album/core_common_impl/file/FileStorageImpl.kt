package com.example.right_corner_album.core_common_impl.file

import android.content.Context
import com.navektest.core_common.file.FileStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class FileStorageImpl @Inject constructor(@ApplicationContext private val context: Context) :
    FileStorage {

    override fun getFile(directory: String, filename: String): File {
        val fileDir = context.getDir(directory, Context.MODE_PRIVATE)
        return File(fileDir, filename)
    }
}