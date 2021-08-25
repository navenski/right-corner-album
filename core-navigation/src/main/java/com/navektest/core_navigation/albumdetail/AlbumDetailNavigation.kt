package com.navektest.core_navigation.albumdetail

import android.app.Activity

interface AlbumDetailNavigation {
    fun navigateToDetail(activity: Activity, albumId: Long)
}