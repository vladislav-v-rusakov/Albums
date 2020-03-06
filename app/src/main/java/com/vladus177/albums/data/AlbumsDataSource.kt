package com.vladus177.albums.data

import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.model.UserModel

interface AlbumsDataSource {

    suspend fun getAllUsers(): List<UserEntry>

    suspend fun saveAllUsers(): Result<List<UserModel>>

    suspend fun getUser(userId: String): Result<UserModel>

    suspend fun saveUser(user: UserModel)

    suspend fun setFavoriteUser(user: UserModel, favorite: Boolean)

    suspend fun getAlbumsByUserId(userId: Long): List<AlbumEntry>

    suspend fun getAllAlbums(): List<AlbumEntry>

    suspend fun getImagesByAlbumId(albumId: Long): List<ImageEntry>

    suspend fun getAllImages(): List<ImageEntry>
}