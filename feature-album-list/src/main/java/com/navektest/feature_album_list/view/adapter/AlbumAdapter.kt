package com.navektest.feature_album_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.feature_album_list.databinding.AlbumItemBinding
import com.navektest.feature_album_list.model.AlbumItem
import com.navektest.feature_album_list.viewmodel.AlbumListViewModel

class AlbumAdapter(private val viewModel: AlbumListViewModel, private val filePathProvider: FilePathProvider, private val scope: LifecycleCoroutineScope) : PagingDataAdapter<AlbumItem, AlbumItemViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        return AlbumItemViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),viewModel, filePathProvider, scope)
    }

    companion object{

        val diffCallback = object : DiffUtil.ItemCallback<AlbumItem>() {
            override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}