package com.navektest.feature_album_list.repository.datasource.remote

import com.google.gson.annotations.SerializedName

data class AlbumResponse (
    @SerializedName("albumId") val albumId : Long,
    @SerializedName("id") val id : Long,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String,
    @SerializedName("thumbnailUrl") val thumbnailUrl : String
)