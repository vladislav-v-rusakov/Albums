package com.vladus177.albums.data

import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel

interface AlbumsDataSource {
    suspend fun getAllUsers(): List<UserEntry>

    suspend fun saveAllUsers(): Result<List<UserModel>>

    suspend fun getUser(userId: String): Result<UserModel>

    suspend fun saveUser(user: UserModel)

    suspend fun setFavoriteUser(user: UserModel, favorite: Boolean)

}