package com.navektest.feature_album_list.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["isLoading", "hasNoAlbums"], requireAll = true)
    fun setVisibility(
        view: View, isLoading: Boolean, hasNoAlbums: Boolean
    ) {
        val isVisible = !isLoading && hasNoAlbums
        if (isVisible)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }

}