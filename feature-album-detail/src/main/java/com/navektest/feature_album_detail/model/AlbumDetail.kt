package com.navektest.feature_album_detail.model

data class AlbumDetail(val id: Long,
                       val title: String,
                       val thumbnailUrl: String,
                       val pictureUrl: String) {
    companion object {
        val EMPTY = AlbumDetail(0, "", "", "")
    }
}