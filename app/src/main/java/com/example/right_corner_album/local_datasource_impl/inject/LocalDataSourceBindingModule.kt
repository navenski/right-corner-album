package com.example.right_corner_album.local_datasource_impl.inject

import android.content.Context
import androidx.room.Room
import com.example.right_corner_album.local_datasource_impl.AppDatabase
import com.example.right_corner_album.local_datasource_impl.dao.AlbumDao
import com.example.right_corner_album.local_datasource_impl.datasource.AlbumLocalDataSourceImpl
import com.navektest.core_database.AlbumLocalDatasource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceBindingModule {
    @Binds
    abstract fun bindAlbumLocalDataSource(
        dataSource: AlbumLocalDataSourceImpl
    ): AlbumLocalDatasource
}

@Module
@InstallIn(SingletonComponent::class)
object  LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()

    @Provides
    fun provideAlbumDao(database: AppDatabase): AlbumDao = database.albumDao()


        private const val DATABASE_NAME = "albumDb"
}