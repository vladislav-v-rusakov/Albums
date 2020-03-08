package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(private val repository: AlbumRepository) {
    fun getAlbumList(userId: Long, forceUpdate: Boolean): Observable<List<AlbumModel>> =
        repository.getAlbumList(userId, forceUpdate)
}
