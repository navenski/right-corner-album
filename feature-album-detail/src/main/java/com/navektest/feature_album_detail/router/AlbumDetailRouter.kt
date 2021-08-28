package com.navektest.feature_album_detail.router

import com.navektest.core_navigation.back.BackNavigation
import javax.inject.Inject

class AlbumDetailRouter @Inject constructor(private val backNavigation: BackNavigation) {

    fun close() {
        backNavigation.navigateBack()
    }
}