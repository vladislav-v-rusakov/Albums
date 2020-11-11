package com.vladus177.albums.data.repository

import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.mapper.ImageDataMapper
import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.domain.AlbumRepository
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AlbumsDataRepository @Inject constructor(
    private val albumRemoteSource: AlbumRemoteSource,
    private val albumLocalSource: AlbumsLocalSource,
    private val albumMapper: AlbumDataMapper,
    private val imageMapper: ImageDataMapper,
    private val userMapper: UserDataMapper
) : AlbumRepository {

    override fun getAlbumList(userId: Long, forceUpdate: Boolean): Observable<List<AlbumModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                albumRemoteSource.getAlbumList(userId).map { remoteUsers ->
                    with(albumMapper) {
                        remoteUsers.map { it.fromDataToDomain() }
                    }
                }.doOnNext { albums ->
                    insertAllAlbums(albums)
                },
                albumLocalSource.getAlbumList(userId).map { localUsers ->
                    with(albumMapper) {
                        localUsers.map { it.fromEntityToDomain() }
                    }
                }
            )
        } else {
            albumLocalSource.getAlbumList(userId).map { localUsers ->
                with(albumMapper) {
                    localUsers.map { it.fromEntityToDomain() }
                }
            }
        }
    }

    override fun getImageList(albumId: Long, forceUpdate: Boolean): Observable<List<ImageModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                albumRemoteSource.getImageList(albumId).map { remoteImages ->
                    with(imageMapper) {
                        remoteImages.map { it.fromDataToDomain() }
                    }
                }.doOnNext { users ->
                    insertAllImages(users)
                },
                albumLocalSource.getImageList(albumId).map { localImages ->
                    with(imageMapper) {
                        localImages.map { it.fromEntityToDomain() }
                    }
                }
            )
        } else {
            albumLocalSource.getImageList(albumId).map { localImages ->
                with(imageMapper) {
                    localImages.map { it.fromEntityToDomain() }
                }
            }
        }
    }

    override fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                albumRemoteSource.getUserList().map { remoteUsers ->
                    with(userMapper) {
                        remoteUsers.map { it.fromDataToDomain() }
                    }
                }.doOnNext { users ->
                    insertAllUsers(users)
                },
                albumLocalSource.getUserList().map { localUsers ->
                    with(userMapper) {
                        localUsers.map { it.fromEntityToDomain() }
                    }
                }
            )
        } else {
            albumLocalSource.getUserList().map { localUsers ->
                with(userMapper) {
                    localUsers.map { it.fromEntityToDomain() }
                }
            }
        }
    }

    override fun insertAllUsers(users: List<UserModel>) =
        albumLocalSource.insertAllUsers(users.map { with(userMapper) { it.fromDomainToEntity() } })

    override fun insertAllAlbums(album: List<AlbumModel>) =
        albumLocalSource.insertAllAlbums(album.map { with(albumMapper) { it.fromDomainToEntity() } })

    override fun insertAllImages(images: List<ImageModel>) =
        albumLocalSource.insertAllImages(images.map { with(imageMapper) { it.fromDomainToEntity() } })

    override fun setFavoriteUser(userId: Long, favorite: Boolean): Completable =
        albumLocalSource.setFavoriteUser(userId, favorite)
}