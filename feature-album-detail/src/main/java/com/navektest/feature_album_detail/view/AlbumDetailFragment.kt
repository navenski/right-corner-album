package com.navektest.feature_album_detail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.feature_album_detail.databinding.AlbumDetailFragmentBinding
import com.navektest.feature_album_detail.viewmodel.AlbumDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {
    @Inject lateinit var filePathProvider: FilePathProvider

    private val viewModel: AlbumDetailViewModel by viewModels()
    private lateinit var binding: AlbumDetailFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = AlbumDetailFragmentBinding.inflate(inflater)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.scope = lifecycleScope
        binding.filePathProvider = filePathProvider

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val albumId = arguments?.getLong(ALBUM_ID) ?: -1
        viewModel.initWithAlbumId(albumId)
    }

    companion object {
        fun newInstance() = AlbumDetailFragment()
         const val ALBUM_ID = "albumId"
    }
}