package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable

interface AlbumRepository {

    fun getAlbumList(userId: Long, forceUpdate: Boolean): Observable<List<AlbumModel>>

    fun insertAll(album: List<AlbumModel>)
}