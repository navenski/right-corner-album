package com.example.right_corner_album.core_common_impl.networking

import okhttp3.Response
import okio.BufferedSink
import okio.BufferedSource
import okio.buffer
import okio.sink
import java.io.File
import javax.inject.Inject

/**
 * Write [BufferedSource] to [File]
 */
class BufferedSourceFileWriter @Inject constructor(){

    /**
     * Write [BufferedSource] to [File]
     */
     fun write(source: BufferedSource, file: File): Boolean {
        return try {
            val sink: BufferedSink =
                file.sink()
                    .buffer()
            sink.writeAll(source)
            sink.close()
            true
        } catch (exception: Exception) {
            false
        }
    }
}