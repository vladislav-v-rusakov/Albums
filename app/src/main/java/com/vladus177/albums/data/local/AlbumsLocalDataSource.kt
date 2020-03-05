package com.vladus177.albums.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.vladus177.albums.data.AlbumsDataSource
import com.vladus177.albums.data.Result
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel

class AlbumsLocalDataSource internal constructor(
    private val usersDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsDataSource {

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