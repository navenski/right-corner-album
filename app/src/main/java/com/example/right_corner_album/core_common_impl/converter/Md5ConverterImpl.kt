package com.example.right_corner_album.core_common_impl.converter

import com.navektest.core_common.converter.Md5Converter
import java.security.MessageDigest
import javax.inject.Inject

class Md5ConverterImpl @Inject constructor() : Md5Converter {

    override fun convert(input: String): String {
        if (input.trim()
                .isEmpty())
            return ""

        val bytes =
            MessageDigest.getInstance("MD5")
                .digest(input.toByteArray())
        return convertToHex(bytes)
    }

    private fun convertToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02x".format(it) }
    }
}