package com.navektest.feature_album_list.repository.datasource.remote

import retrofit2.http.GET

interface AlbumApi {
    @GET("technical-test.json")
    suspend fun getAlbums(): List<AlbumResponse>
}