package com.navektest.feature_album_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.core_common.model.StateDataModel
import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.AlbumDetailRepository
import com.navektest.feature_album_detail.repository.mapper.AlbumDetailMapper
import com.navektest.feature_album_detail.router.AlbumDetailRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val repository: AlbumDetailRepository,
                                               private val albumDetailMapper: AlbumDetailMapper) : ViewModel() {
    private val mutableLiveData = MutableLiveData<StateDataModel<AlbumDetail>>()
    fun getLiveData(): LiveData<StateDataModel<AlbumDetail>> = mutableLiveData
    private var routerWeakRef = WeakReference<AlbumDetailRouter>(null)

    fun initWithAlbumId(albumId: Long) {
        viewModelScope.launch {
            mutableLiveData.value = StateDataModel.loading()
            val album = repository.getAlbum(albumId)
            mutableLiveData.value =
                if (album != null) StateDataModel.success(albumDetailMapper.map(album)) else StateDataModel.error()
        }
    }

    fun bindRouter(router: AlbumDetailRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun close() {
        routerWeakRef.get()
            ?.close()
    }
}