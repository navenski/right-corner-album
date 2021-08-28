package com.navektest.feature_album_list.inject

import android.app.Activity
import android.content.Context
import com.navektest.core_ui.SnackBarDisplayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
object AlbumListFragmentModule {

    @Provides
    fun provideSnackBarDisplayer(@ActivityContext context: Context): SnackBarDisplayer =
        SnackBarDisplayer(context as Activity)

}