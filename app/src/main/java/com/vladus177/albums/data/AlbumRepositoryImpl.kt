package com.vladus177.albums.data

import com.vladus177.albums.common.Result
import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.mapper.ImageDataMapper
import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.di.ApplicationModule.AlbumsRemoteDataSource
import com.vladus177.albums.di.ApplicationModule.AlbumsLocalDataSource
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    @AlbumsRemoteDataSource private val remoteDataSource: AlbumsDataSource,
    @AlbumsLocalDataSource private val localDataSource: AlbumsDataSource,
    private val userMapper: UserDataMapper,
    private val albumMapper: AlbumDataMapper,
    private val imageMapper: ImageDataMapper
) : AlbumsRepository {

    override suspend fun getImagesByAlbumId(forceUpdate: Boolean, albumId: Long): List<ImageModel> =
        remoteDataSource.getImagesByAlbumId(albumId)
            .map {
                with(imageMapper) {
                    it.fromDataToDomain()
                }
            }

    override suspend fun getAllImages(forceUpdate: Boolean): List<ImageModel> =
        remoteDataSource.getAllImages().map {
            with(imageMapper) {
                it.fromDataToDomain()
            }
        }

    override suspend fun getAlbumsByUserId(forceUpdate: Boolean, userId: Long): List<AlbumModel> =
        remoteDataSource
            .getAlbumsByUserId(userId)
            .map {
                with(albumMapper) {
                    it.fromDataToDomain()
                }
            }

    override suspend fun getAllAlbums(forceUpdate: Boolean): List<AlbumModel> = remoteDataSource
        .getAllAlbums()
        .map {
            with(albumMapper) {
                it.fromDataToDomain()
            }
        }

    override suspend fun saveAllUsers(): Result<List<UserModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUser(userId: String): Result<UserModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllUsers(forceUpdate: Boolean): List<UserModel> = remoteDataSource
        .getAllUsers()
        .map {
            with(userMapper) {
                it.fromDataToDomain()
            }
        }


    override suspend fun saveUser(user: UserModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setFavoriteUser(user: UserModel, favorite: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}