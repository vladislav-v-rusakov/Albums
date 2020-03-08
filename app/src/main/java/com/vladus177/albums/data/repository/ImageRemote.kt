package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable

interface ImageRemote {

    fun getImageList(albumId: Long): Observable<List<ImageModel>>

}