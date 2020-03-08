package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable

interface AlbumLocal {

    fun getAlbumList(userId: Long): Observable<List<AlbumModel>>

    fun insertAll(images: List<AlbumModel>)

}