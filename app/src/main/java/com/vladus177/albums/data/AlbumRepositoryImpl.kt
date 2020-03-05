package com.vladus177.albums.data

import com.vladus177.albums.common.Result
import com.vladus177.albums.data.mapper.UserDataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.vladus177.albums.di.ApplicationModule.AlbumsRemoteDataSource
import com.vladus177.albums.di.ApplicationModule.AlbumsLocalDataSource
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    @AlbumsRemoteDataSource private val tasksRemoteDataSource: AlbumsDataSource,
    @AlbumsLocalDataSource private val tasksLocalDataSource: AlbumsDataSource,
    private val mapper: UserDataMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsRepository {
    override suspend fun saveAllUsers(): Result<List<UserModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUser(userId: String): Result<UserModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllUsers(forceUpdate: Boolean): List<UserModel> = tasksRemoteDataSource
        .getAllUsers()
        .map {
            with(mapper) {
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