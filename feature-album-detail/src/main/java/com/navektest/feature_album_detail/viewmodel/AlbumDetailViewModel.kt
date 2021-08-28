package com.navektest.feature_album_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navektest.feature_album_detail.model.AlbumDetail
import com.navektest.feature_album_detail.repository.AlbumDetailRepository
import com.navektest.feature_album_detail.router.AlbumDetailRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val repository: AlbumDetailRepository) : ViewModel() {
    private val mutableLiveData = MutableLiveData<AlbumDetail>()
    fun getLiveData(): LiveData<AlbumDetail> = mutableLiveData

    private var routerWeakRef = WeakReference<AlbumDetailRouter>(null)

    fun initWithAlbumId(albumId: Long) {
        viewModelScope.launch {
            mutableLiveData.value = repository.getAlbum(albumId)
        }
    }

    fun bindRouter(router: AlbumDetailRouter){
        routerWeakRef = WeakReference(router)
    }

    fun close(){
        routerWeakRef.get()?.close()
    }
}