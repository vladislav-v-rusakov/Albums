package com.vladus177.albums.data.local

import com.vladus177.albums.data.local.model.AlbumEntity
import com.vladus177.albums.data.local.model.ImageEntity
import com.vladus177.albums.data.local.model.UserEntity
import com.vladus177.albums.data.repository.AlbumsLocalSource
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AlbumsLocalSourceRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao, private val userDao: UsersDao, private val imageDao: ImageDao
) : AlbumsLocalSource {

    override fun getAlbumList(userId: Long): Observable<List<AlbumEntity>> =
        Observable.fromCallable {
            albumDao.getAlbumsByUserId(userId)
        }

    override fun insertAllAlbums(albums: List<AlbumEntity>) = albumDao.insertAll(albums)

    override fun getImageList(albumId: Long): Observable<List<ImageEntity>> =
        Observable.fromCallable {
            imageDao.getAllImages()
        }

    override fun insertAllImages(images: List<ImageEntity>) {
        imageDao.insertAll(images)
    }

    override fun setFavoriteUser(userId: Long, favorite: Boolean): Completable =
        userDao.updateFavorite(userId, favorite)

    override fun insertAllUsers(users: List<UserEntity>) {
        userDao.insertAll(users)
    }

    override fun getUserList(): Observable<List<UserEntity>> = Observable.fromCallable {
        userDao.getAllUsers()
    }

}
