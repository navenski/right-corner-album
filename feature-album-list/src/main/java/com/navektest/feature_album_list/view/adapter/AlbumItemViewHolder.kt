package com.navektest.feature_album_list.view.adapter

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.feature_album_list.databinding.AlbumItemBinding
import com.navektest.feature_album_list.model.AlbumItem
import com.navektest.feature_album_list.viewmodel.AlbumListViewModel

class AlbumItemViewHolder(private val binding: AlbumItemBinding,
                          private val viewModel: AlbumListViewModel,
                          private val filePathProvider: FilePathProvider,
                          private val scope: LifecycleCoroutineScope
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: AlbumItem) {
        binding.filePathProvider = filePathProvider
        binding.viewModel = viewModel
        binding.scope = scope
        binding.model = model

        binding.executePendingBindings()
    }
}