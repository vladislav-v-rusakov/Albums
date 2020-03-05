package vladus177.com.albums.data

import vladus177.com.albums.domain.model.UserModel

interface AlbumsDataSource {
    suspend fun getAllUsers(): Result<List<UserModel>>

    suspend fun saveAllUsers(): Result<List<UserModel>>

    suspend fun getUser(userId: String): Result<UserModel>

    suspend fun saveUser(user: UserModel)

    suspend fun setFavoriteUser(user: UserModel, favorite: Boolean)

}