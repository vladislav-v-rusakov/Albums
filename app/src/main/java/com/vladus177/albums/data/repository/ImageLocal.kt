package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable

interface ImageLocal {

    fun getImageList(albumId: Long): Observable<List<ImageModel>>

    fun insertAll(images: List<ImageModel>)

}