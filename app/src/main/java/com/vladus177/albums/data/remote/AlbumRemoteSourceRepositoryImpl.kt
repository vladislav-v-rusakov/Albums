package com.vladus177.albums.data.remote

import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.data.repository.AlbumRemoteSource
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRemoteSourceRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi
) : AlbumRemoteSource {

    override fun getAlbumList(userId: Long): Observable<List<AlbumEntry>> = restApi.getAlbumsByUserId(userId)


    override fun getImageList(albumId: Long): Observable<List<ImageEntry>> =
        restApi.getImagesByAlbumId(albumId)

    override fun getUserList(): Observable<List<UserEntry>> = restApi.getAllUsers()
}