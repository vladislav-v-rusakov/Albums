package com.vladus177.albums.data.remote

import retrofit2.Call
import retrofit2.http.GET
import com.vladus177.albums.data.remote.model.UserEntry

interface AlbumsRestApi {

    @GET("/users")
    suspend fun getAllUsers(): List<UserEntry>

}