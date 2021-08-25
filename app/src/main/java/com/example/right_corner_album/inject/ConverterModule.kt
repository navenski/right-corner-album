package com.example.right_corner_album.inject

import com.example.right_corner_album.core_common_impl.converter.Md5ConverterImpl
import com.navektest.core_common.converter.Md5Converter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {
    @Provides
    fun provideMd5Converter(): Md5Converter = Md5ConverterImpl()
}