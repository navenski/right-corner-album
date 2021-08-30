package com.example.right_corner_album.navigation_impl

import android.app.Activity
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.findNavController
import timber.log.Timber

fun Activity.tryFindNavController(@IdRes viewId: Int): NavController? {
    return try {
        findNavController(viewId)
    } catch (exception: Exception) {
        Timber.e(exception)
        null
    }
}