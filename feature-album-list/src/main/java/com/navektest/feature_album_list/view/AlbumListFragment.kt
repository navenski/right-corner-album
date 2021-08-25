package com.navektest.feature_album_list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.navektest.core_common.provider.FilePathProvider
import com.navektest.core_ui.itemdecoration.GridSpacingItemDecoration
import com.navektest.feature_album_list.databinding.AlbumListFragmentBinding
import com.navektest.feature_album_list.router.AlbumListRouter
import com.navektest.feature_album_list.view.adapter.AlbumAdapter
import com.navektest.feature_album_list.viewmodel.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    @Inject lateinit var router: AlbumListRouter
    @Inject lateinit var filePathProvider: FilePathProvider

    private val viewModel: AlbumListViewModel by viewModels()

    private lateinit var binding: AlbumListFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = AlbumListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        viewModel.bindRouter(router)
        val adapter = AlbumAdapter(viewModel, filePathProvider)
        val spanCount = 3
        binding.recycler.layoutManager = GridLayoutManager(context, spanCount)
        val itemDecoration = GridSpacingItemDecoration(spanCount, 20, true)
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.albums
                .collectLatest { adapter.submitData(it) }
        }
        binding.recycler.addItemDecoration(itemDecoration)
        binding.refreshListener = SwipeRefreshLayout.OnRefreshListener {
            viewModel.refresh()
        }

        return binding.root
    }

    companion object {
        fun newInstance() = AlbumListFragment()
    }
}