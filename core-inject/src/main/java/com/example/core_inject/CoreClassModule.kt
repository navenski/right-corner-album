package com.example.core_inject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreClassModule {

    @Provides
    fun provideToto(): CoreClass = CoreClass()
}