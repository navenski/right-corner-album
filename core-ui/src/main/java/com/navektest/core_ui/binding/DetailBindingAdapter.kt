package com.navektest.core_ui.binding

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core_ui.R
import com.navektest.core_common.networking.downloder.FileCacheDownloader
import com.navektest.core_ui.canceller.ImageJobsCanceller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.lang.Exception

/**
 *  BindingAdapter.
 */
object DetailBindingAdapter {

    private val imageJobCanceller = ImageJobsCanceller()

    @JvmStatic
    @BindingAdapter(value = ["url", "scope", "pictureProvider"], requireAll = true)
    fun setUrl(image: ImageView, url: String, scope: CoroutineScope, pictureProvider: FileCacheDownloader) {
        image.setImageDrawable(null)
        imageJobCanceller.cancelJob(image)
        scope.launch {
            try {
                val filePath = pictureProvider.tryDownload(url)
                if (filePath.isNotEmpty()) {
                    loadImage(filePath, image)
                }
            } catch (exception: Exception) {
                Timber.e("ImageDataBindingAdapter", "error", exception)
            }
        } .apply {
            imageJobCanceller.addJob(image, this)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["pictureUrl", "thumbnailUrl", "scope", "pictureProvider"], requireAll = true)
    fun setUrls(image: ImageView,
                pictureUrl: String?,
                thumbnailUrl: String?,
                scope: CoroutineScope,
                pictureProvider: FileCacheDownloader
    ) {
        image.setImageDrawable(null)
        imageJobCanceller.cancelJob(image)
        scope.launch {
            try {
                val nonNullPictureUrl = pictureUrl ?: ""
                val nonNullThumbnailUrl = thumbnailUrl ?: ""

                var filePath = pictureProvider.tryDownload(nonNullPictureUrl)
                if (filePath.isEmpty()) {
                    filePath = pictureProvider.tryDownload(nonNullThumbnailUrl)
                }

                if (filePath.isNotEmpty()) {
                    loadImage(filePath, image)
                }
            } catch (exception: Exception) {
                Timber.e("ImageDataBindingAdapter", "error", exception)
            }
        }.apply {
            imageJobCanceller.addJob(image, this)
        }
    }

    private fun loadImage(fileName: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(File(fileName))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("idToText")
    fun setId(
        textView: TextView, id: Long?
    ) {
        id?.let { textView.text = "$it" }
    }

    @JvmStatic
    @BindingAdapter("titleId")
    fun setTitleId(
        toolbar: com.google.android.material.appbar.MaterialToolbar, id: Long?
    ) {
        id?.let { toolbar.title = "$it" }
    }

    @JvmStatic
    @BindingAdapter("onNavigationClick")
    fun onNavigationClick(view: Toolbar, listener: View.OnClickListener) {
        view.setNavigationOnClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun setVisibleOrGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}