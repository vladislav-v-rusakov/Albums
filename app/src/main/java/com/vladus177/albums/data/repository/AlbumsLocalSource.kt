package com.vladus177.albums.data.repository

import com.vladus177.albums.data.local.model.AlbumEntity
import com.vladus177.albums.data.local.model.ImageEntity
import com.vladus177.albums.data.local.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface AlbumsLocalSource {

    fun getAlbumList(userId: Long): Observable<List<AlbumEntity>>

    fun insertAllAlbums(albums: List<AlbumEntity>)

    fun getImageList(albumId: Long): Observable<List<ImageEntity>>

    fun insertAllImages(images: List<ImageEntity>)

    fun getUserList(): Observable<List<UserEntity>>

    fun insertAllUsers(users: List<UserEntity>)

    fun setFavoriteUser(userId: Long, favorite: Boolean) : Completable

}