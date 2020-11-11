package com.vladus177.albums.data.repository

import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.data.remote.model.UserEntry
import io.reactivex.Observable

interface AlbumRemoteSource {

    fun getAlbumList(userId: Long): Observable<List<AlbumEntry>>

    fun getImageList(albumId: Long): Observable<List<ImageEntry>>

    fun getUserList(): Observable<List<UserEntry>>

}