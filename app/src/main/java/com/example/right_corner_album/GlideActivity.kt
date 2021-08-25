package com.example.right_corner_album

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.navektest.core_common.networking.downloder.FileDownloader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.http.Url

import retrofit2.http.GET
import java.io.File
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class GlideActivity : AppCompatActivity() {
    @Inject lateinit var fileDownloader: FileDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        lifecycleScope.launch {
            val file = fileDownloader.downloadFile("https://via.placeholder.com/150/e9603b", "e9603b")
            if (file.isSuccess) {
                loadImage(file.filePath)
            }
        }
    }

    private fun loadImage(fileName: String) {
        val imageView = findViewById<ImageView>(R.id.imageGlide)
        Glide.with(this)
            .load(File(fileName))
            .into(imageView);
    }
}