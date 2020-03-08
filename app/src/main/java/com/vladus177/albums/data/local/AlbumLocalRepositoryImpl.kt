package com.vladus177.albums.data.local

import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.repository.AlbumLocal
import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable
import javax.inject.Inject

class AlbumLocalRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao, private val albumMapper: AlbumDataMapper
) : AlbumLocal {
    override fun getAlbumList(userId: Long): Observable<List<AlbumModel>> {
        return Observable.fromCallable<List<AlbumModel>> {
            albumDao.getAlbumsByUserId(userId).map { with(albumMapper) { it.fromEntityToDomain() } }
        }
    }

    override fun insertAll(images: List<AlbumModel>) {
        albumDao.insertAll(images.map { with(albumMapper) { it.fromDomainToEntity() } })
    }

}