package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable

interface ImageRepository {

    fun getImageList(albumId: Long, forceUpdate: Boolean): Observable<List<ImageModel>>

    fun insertAll(images: List<ImageModel>)

}