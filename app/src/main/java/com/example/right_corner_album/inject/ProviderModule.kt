package com.example.right_corner_album.inject

import com.example.right_corner_album.core_common_impl.provider.FilePathProviderImpl
import com.navektest.core_common.converter.Md5Converter
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.downloder.FileDownloader
import com.navektest.core_common.provider.FilePathProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    fun provideFilePathProvider(dispatcherProvider: CoroutineDispatcherProvider,
                                fileDownloader: FileDownloader,
                                md5Converter: Md5Converter): FilePathProvider =
        FilePathProviderImpl(dispatcherProvider, fileDownloader, md5Converter)
}