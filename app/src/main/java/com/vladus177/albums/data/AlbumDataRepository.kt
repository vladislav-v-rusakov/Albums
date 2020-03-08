package com.vladus177.albums.data

import com.vladus177.albums.data.source.AlbumDataSourceFactory
import com.vladus177.albums.domain.AlbumRepository
import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumDataRepository @Inject constructor(
    private val factory: AlbumDataSourceFactory
) : AlbumRepository {

    override fun getAlbumList(userId: Long, forceUpdate: Boolean): Observable<List<AlbumModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                factory.getRemote().getAlbumList(userId).doOnNext { users ->
                    insertAll(users)
                }.subscribeOn(Schedulers.io()),
                factory.getLocal().getAlbumList(userId).subscribeOn(Schedulers.io())
            )

        } else {
            factory.getLocal().getAlbumList(userId)
        }
    }

    override fun insertAll(album: List<AlbumModel>) = factory.getLocal().insertAll(album)


}