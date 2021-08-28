package com.example.right_corner_album.navigation_impl

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.right_corner_album.R
import com.navektest.core_navigation.albumdetail.AlbumDetailNavigation
import com.navektest.feature_album_detail.view.AlbumDetailFragment

class AlbumDetailNavigationImpl(private val activity: Activity) : AlbumDetailNavigation {
    override fun navigateToDetail(albumId: Long) {
        val bundle = bundleOf(AlbumDetailFragment.ALBUM_ID to albumId)
        activity.findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_albumListFragment_to_albumDetailFragment, bundle)
    }
}