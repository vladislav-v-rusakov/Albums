package com.vladus177.albums.data

import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.common.Result
import com.vladus177.albums.domain.model.AlbumModel

interface AlbumsRepository {

    suspend fun getAllUsers(forceUpdate: Boolean): List<UserModel>

    suspend fun saveAllUsers(): Result<List<UserModel>>

    suspend fun getUser(userId: String): Result<UserModel>

    suspend fun saveUser(user: UserModel)

    suspend fun setFavoriteUser(user: UserModel, favorite: Boolean)


    suspend fun getAlbumsByUserId(forceUpdate: Boolean, userId: Long): List<AlbumModel>

    suspend fun getAllAlbums(forceUpdate: Boolean): List<AlbumModel>

}