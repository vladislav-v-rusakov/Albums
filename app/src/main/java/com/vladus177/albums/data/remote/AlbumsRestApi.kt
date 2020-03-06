package com.vladus177.albums.data.remote

import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import retrofit2.http.GET
import com.vladus177.albums.data.remote.model.UserEntry
import retrofit2.http.Query

interface AlbumsRestApi {

    @GET("/users")
    suspend fun getAllUsers(): List<UserEntry>

    @GET("/albums")
    suspend fun getAllAlbums(): List<AlbumEntry>

    @GET("/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Long): List<AlbumEntry>

    @GET("/photos")
    suspend fun getAllImages(): List<ImageEntry>

    @GET("/photos")
    suspend fun getImagesByAlbumId(@Query("albumId") albumId: Long): List<ImageEntry>

}