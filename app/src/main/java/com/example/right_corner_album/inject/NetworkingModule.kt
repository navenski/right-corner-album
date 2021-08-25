package com.example.right_corner_album.inject

import android.content.Context
import com.example.right_corner_album.core_common_impl.networking.FileDownloaderImpl
import com.example.right_corner_album.core_common_impl.networking.NetworkStateAvailabilityImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.navektest.core_common.Constant
import com.navektest.core_common.provider.CoroutineDispatcherProvider
import com.navektest.core_common.networking.NetworkStateAvailability
import com.navektest.core_common.networking.downloder.FileDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val URL = "https://static.leboncoin.fr/img/shared/"

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun provideNetworkStateAvailability(@ApplicationContext context: Context): NetworkStateAvailability =
        NetworkStateAvailabilityImpl(context)

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideFileDownloader(@ApplicationContext context: Context,
                              okHttpClient: OkHttpClient,
                              coroutineDispatcherProvider: CoroutineDispatcherProvider): FileDownloader =
        FileDownloaderImpl(context, coroutineDispatcherProvider, okHttpClient, Constant.pictureDirectoryName)


}