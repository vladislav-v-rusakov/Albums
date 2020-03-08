package com.vladus177.albums.data

import com.vladus177.albums.data.source.ImageDataSourceFactory
import com.vladus177.albums.domain.ImageRepository
import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageDataRepository @Inject constructor(
    private val factory: ImageDataSourceFactory

) : ImageRepository {

    override fun getImageList(albumId: Long, forceUpdate: Boolean): Observable<List<ImageModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                factory.getRemote().getImageList(albumId).doOnNext { users ->
                    insertAll(users)
                }.subscribeOn(Schedulers.io()),
                factory.getLocal().getImageList(albumId).subscribeOn(
                    Schedulers.io()
                )
            )
        } else {
            factory.getLocal().getImageList(albumId)
        }
    }

    override fun insertAll(images: List<ImageModel>) = factory.getLocal().insertAll(images)
}
