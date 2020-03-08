package com.vladus177.albums.data.remote

import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import retrofit2.http.GET
import com.vladus177.albums.data.remote.model.UserEntry
import io.reactivex.Observable
import retrofit2.http.Query

interface AlbumsRestApi {

    @GET("/users")
    fun getAllUsers(): Observable<List<UserEntry>>

    @GET("/albums")
    fun getAlbumsByUserId(@Query("userId") userId: Long): Observable<List<AlbumEntry>>

    @GET("/photos")
    fun getImagesByAlbumId(@Query("albumId") albumId: Long): Observable<List<ImageEntry>>

}