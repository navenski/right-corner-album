package com.example.right_corner_album.inject

import android.content.Context
import com.example.right_corner_album.core_common_impl.dispatcher.CoroutineDispatcherProviderImpl
import com.example.right_corner_album.core_common_impl.resource.ResourceResolverImpl
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.resource.ResourceResolver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider = CoroutineDispatcherProviderImpl()

    @Singleton
    @Provides
    fun provideResourceResolver(@ApplicationContext context: Context): ResourceResolver = ResourceResolverImpl(context)
}