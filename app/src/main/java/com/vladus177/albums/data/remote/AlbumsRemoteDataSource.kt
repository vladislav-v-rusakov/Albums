package com.vladus177.albums.data.remote

import com.vladus177.albums.data.AlbumsDataSource
import com.vladus177.albums.data.Result
import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel

class AlbumsRemoteDataSource internal constructor(
    private val apiService: AlbumsRestApi
) : AlbumsDataSource {

    override suspend fun getImagesByAlbumId(albumId: Long): List<ImageEntry> = apiService.getImagesByAlbumId(albumId)

    override suspend fun getAllImages(): List<ImageEntry> = apiService.getAllImages()

    override suspend fun getAlbumsByUserId(userId: Long): List<AlbumEntry> = apiService.getAlbumsByUserId(userId)

    override suspend fun getAllAlbums(): List<AlbumEntry> = apiService.getAllAlbums()

    override suspend fun getAllUsers(): List<UserEntry>  = apiService.getAllUsers()

    override suspend fun saveAllUsers(): Result<List<UserModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUser(userId: String): Result<UserModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveUser(user: UserModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setFavoriteUser(user: UserModel, favorite: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}