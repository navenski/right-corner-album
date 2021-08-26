package com.navektest.feature_album_list.router

import android.app.Activity
import android.util.Log
import com.navektest.core_common.resource.ResourceResolver
import com.navektest.core_navigation.albumdetail.AlbumDetailNavigation
import com.navektest.core_ui.SnackBarDisplayer
import com.navektest.core_ui.SnackBarDurationType
import com.navektest.core_ui.SnackBarMessageType
import com.navektest.feature_album_list.R
import javax.inject.Inject

class AlbumListRouter @Inject constructor(private val activity: Activity,
                      private val detailNavigation: AlbumDetailNavigation,
                      private val snackBarDisplayer: SnackBarDisplayer, private val resourceResolver: ResourceResolver) {

    fun navigateToDetails(albumId: Long) {
        detailNavigation.navigateToDetail(activity, albumId)
    }

    fun displaySnackBarSuccess() {
        snackBarDisplayer.display(resourceResolver.getString(R.string.success_synchronisation),
                                  SnackBarMessageType.SUCCESS,
                                  snackLength = SnackBarDurationType.SHORT)
    }

    fun displaySnackBarError(isNetworkAvailabilityError: Boolean) {
        val stringResource = if (isNetworkAvailabilityError) R.string.offline_mode else R.string.echec_synchronisation
        snackBarDisplayer.display(resourceResolver.getString(stringResource),
                                  SnackBarMessageType.ERROR,
                                  snackLength = SnackBarDurationType.LONG)
    }
}