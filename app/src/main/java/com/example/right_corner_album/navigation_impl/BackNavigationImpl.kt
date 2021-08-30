package com.example.right_corner_album.navigation_impl

import android.app.Activity
import androidx.navigation.findNavController
import com.example.right_corner_album.R
import com.navektest.core_navigation.back.BackNavigation

class BackNavigationImpl(private val activity: Activity) : BackNavigation {
    override fun navigateBack() {
        activity.tryFindNavController(R.id.nav_host_fragment)?.popBackStack()
    }
}