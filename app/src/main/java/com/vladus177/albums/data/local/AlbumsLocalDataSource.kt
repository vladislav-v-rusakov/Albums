package com.vladus177.albums.data.local

import com.vladus177.albums.data.AlbumsDataSource
import com.vladus177.albums.data.Result
import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel

class AlbumsLocalDataSource internal constructor(
    private val usersDao: UsersDao,
    private val albumsDao: AlbumDao
) : AlbumsDataSource {
    override suspend fun getAlbumsByUserId(userId: Long): List<AlbumEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllAlbums(): List<AlbumEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

    override suspend fun getAllUsers(): List<UserEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}