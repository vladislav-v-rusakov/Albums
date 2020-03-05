package com.vladus177.albums.data

import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.common.Result

interface AlbumsRepository {

    suspend fun getAllUsers(forceUpdate: Boolean): List<UserModel>

    suspend fun saveAllUsers(): Result<List<UserModel>>

    suspend fun getUser(userId: String): Result<UserModel>

    suspend fun saveUser(user: UserModel)

    suspend fun setFavoriteUser(user: UserModel, favorite: Boolean)

}