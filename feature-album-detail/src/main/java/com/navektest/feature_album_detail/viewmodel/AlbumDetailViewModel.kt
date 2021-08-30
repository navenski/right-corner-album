package com.navektest.feature_album_detail.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val mutableLiveData = MutableLiveData<AlbumDetail>()
    fun getLiveData(): LiveData<AlbumDetail> = mutableLiveData
    val isLoading = ObservableBoolean(false)
    private var routerWeakRef = WeakReference<AlbumDetailRouter>(null)

    fun initWithAlbumId(albumId: Long) {
        viewModelScope.launch {
            isLoading.set(true)
            val album = repository.getAlbum(albumId)
            if (album == null) {

            } else
                mutableLiveData.value = albumDetailMapper.map(album)
        }
        isLoading.set(false)
    }

    fun bindRouter(router: AlbumDetailRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun close() {
        routerWeakRef.get()
            ?.close()
    }
}