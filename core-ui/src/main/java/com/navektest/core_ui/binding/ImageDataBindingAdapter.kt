package com.navektest.core_ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core_ui.R
import com.navektest.core_common.provider.FilePathProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

object ImageDataBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["url", "scope", "pictureProvider"], requireAll = true)
    fun setUrl(image: ImageView, url: String, scope: CoroutineScope, pictureProvider: FilePathProvider) {
        image.setImageDrawable(null)
        scope.launch {
            val filePath = pictureProvider.provide(url)
            if (filePath.isNotEmpty()) {
                loadImage(filePath, image)
            }else
                image.setImageResource(R.drawable.ic_gallery)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["pictureUrl", "thumbnailUrl", "scope", "pictureProvider"], requireAll = true)
    fun setUrls(image: ImageView,
                pictureUrl: String?,
                thumbnailUrl: String?,
                scope: CoroutineScope,
                pictureProvider: FilePathProvider) {
        image.setImageDrawable(null)
        scope.launch {
            val nonNullPictureUrl = pictureUrl ?: ""
            val nonNullThumbnailUrl = thumbnailUrl ?: ""

            var filePath = pictureProvider.provide(nonNullPictureUrl)
            if (filePath.isEmpty()) {
                filePath = pictureProvider.provide(nonNullThumbnailUrl)
            }

            if (filePath.isNotEmpty()) {
                loadImage(filePath, image)
            }else
                image.setImageResource(R.drawable.ic_gallery)
        }
    }

    private fun loadImage(fileName: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(File(fileName))
            .error(R.drawable.ic_gallery)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(imageView)
    }
}