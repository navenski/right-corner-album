package com.example.right_corner_album.core_common_impl.inject

import com.example.right_corner_album.core_common_impl.converter.Md5ConverterImpl
import com.example.right_corner_album.core_common_impl.dispatcher.CoroutineDispatcherProviderImpl
import com.example.right_corner_album.core_common_impl.networking.FileDownloaderImpl
import com.example.right_corner_album.core_common_impl.networking.FileStorageDirectoryName
import com.example.right_corner_album.core_common_impl.networking.NetworkStateAvailabilityImpl
import com.example.right_corner_album.core_common_impl.provider.FileCacheDownloaderImpl
import com.example.right_corner_album.core_common_impl.resource.ResourceResolverImpl
import com.navektest.core_common.converter.Md5Converter
import com.navektest.core_common.networking.NetworkStateAvailability
import com.navektest.core_common.networking.downloder.FileDownloader
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.downloder.FileCacheDownloader
import com.navektest.core_common.resource.ResourceResolver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreCommonBindingModule {
    @Binds
    abstract fun bindMd5Converter(
        converter: Md5ConverterImpl
    ): Md5Converter

    @Binds
    abstract fun bindCoroutineDispatcherProvider(
        source: CoroutineDispatcherProviderImpl
    ): CoroutineDispatcherProvider

    @Binds
    abstract fun bindFileDownloaderImpl(
        source: FileDownloaderImpl
    ): FileDownloader

    @Binds
    abstract fun bindFilePathProviderl(
        source: FileCacheDownloaderImpl
    ): FileCacheDownloader

    @Binds
    abstract fun bindResourceResolver(
        source: ResourceResolverImpl
    ): ResourceResolver

    @Binds
    abstract fun bindNetworkStateAvailabilityImpl(
        source: NetworkStateAvailabilityImpl
    ): NetworkStateAvailability
}

@Module
@InstallIn(SingletonComponent::class)
object CoreCommonModule {

    @FileStorageDirectoryName
    @Provides
    fun provideDirectoryName(): String = DIRECTORY_NAME

    private const val DIRECTORY_NAME = "download"
}
