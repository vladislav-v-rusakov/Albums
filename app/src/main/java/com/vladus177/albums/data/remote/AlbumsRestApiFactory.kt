package com.vladus177.albums.data.remote

import com.vladus177.albums.data.Constants.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class AlbumsRestApiFactory {

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val albumsApi: AlbumsRestApi = retrofit().create(AlbumsRestApi::class.java)

}