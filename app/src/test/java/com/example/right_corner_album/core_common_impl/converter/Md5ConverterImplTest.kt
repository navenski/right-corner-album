package com.example.right_corner_album.core_common_impl.converter

import com.navektest.core_common.converter.Md5Converter
import org.junit.Assert
import org.junit.Test

class Md5ConverterImplTest{

    @Test
     fun testConvertEmpty() {
        val converter : Md5Converter = Md5ConverterImpl()
        val result = converter.convert("")
        Assert.assertEquals("", result)
    }

    @Test
    fun testConvertUrl() {
        val converter : Md5Converter = Md5ConverterImpl()
        val result = converter.convert("https://dribbble.com/")
        Assert.assertEquals("401bbe696e71590df07ac89e875979a3", result)
    }

}