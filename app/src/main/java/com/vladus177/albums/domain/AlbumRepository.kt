package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Completable
import io.reactivex.Observable

interface AlbumRepository {

    fun getAlbumList(userId: Long, forceUpdate: Boolean): Observable<List<AlbumModel>>

    fun insertAllAlbums(album: List<AlbumModel>)

    fun getImageList(albumId: Long, forceUpdate: Boolean): Observable<List<ImageModel>>

    fun insertAllImages(images: List<ImageModel>)

    fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>>

    fun setFavoriteUser(userId: Long, favorite: Boolean): Completable

    fun insertAllUsers(users: List<UserModel>)
}