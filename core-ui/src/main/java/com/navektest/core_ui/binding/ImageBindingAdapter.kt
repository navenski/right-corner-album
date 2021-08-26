package com.navektest.core_ui.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core_ui.R
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.core_ui.canceller.ImageJobsCanceller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception

/**
 * ImageView bindingAdapter.
 *
 */
object ImageBindingAdapter {

    private val imageJobCanceller = ImageJobsCanceller()

    @JvmStatic
    @BindingAdapter(value = ["url", "scope", "pictureProvider"], requireAll = true)
    fun setUrl(image: ImageView, url: String, scope: CoroutineScope, pictureProvider: FilePathProvider) {
        image.setImageDrawable(null)
        imageJobCanceller.cancelJob(image)
        scope.launch {
            try {
                val filePath = pictureProvider.provide(url)
                if (filePath.isNotEmpty()) {
                    loadImage(filePath, image)
                } else
                    image.setImageResource(R.drawable.ic_gallery)
            } catch (exception: Exception) {
                Log.e("ImageDataBindingAdapter", "error", exception)
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
                pictureProvider: FilePathProvider) {
        image.setImageDrawable(null)
        scope.launch {
            try {
                val nonNullPictureUrl = pictureUrl ?: ""
                val nonNullThumbnailUrl = thumbnailUrl ?: ""

                var filePath = pictureProvider.provide(nonNullPictureUrl)
                if (filePath.isEmpty()) {
                    filePath = pictureProvider.provide(nonNullThumbnailUrl)
                }

                if (filePath.isNotEmpty()) {
                    loadImage(filePath, image)
                } else
                    image.setImageResource(R.drawable.ic_gallery)
            } catch (exception: Exception) {
                Log.e("ImageDataBindingAdapter", "error", exception)
            }
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