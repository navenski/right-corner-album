package com.navektest.feature_album_list.view.adapter

import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.lifecycleScope
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.core_ui.LifecycleViewHolder
import com.navektest.feature_album_list.databinding.AlbumItemBinding
import com.navektest.feature_album_list.model.AlbumItem
import com.navektest.feature_album_list.viewmodel.AlbumListViewModel

class AlbumItemViewHolder(private val binding: AlbumItemBinding,
                          private val viewModel: AlbumListViewModel,
                          private val filePathProvider: FilePathProvider) :
    LifecycleViewHolder(binding.root) {

    override val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    fun bind(model: AlbumItem) {
        binding.filePathProvider = filePathProvider
        binding.scope = lifecycleScope
        binding.viewModel = viewModel
        if (binding.lifecycleOwner != this)
            binding.lifecycleOwner = this

        binding.model = model

        binding.executePendingBindings()
    }
}