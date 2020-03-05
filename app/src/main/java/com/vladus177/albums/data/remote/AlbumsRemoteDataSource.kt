package vladus177.com.albums.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import vladus177.com.albums.data.AlbumsDataSource
import vladus177.com.albums.data.Result
import vladus177.com.albums.domain.model.UserModel

class AlbumsRemoteDataSource internal constructor(
    private val apiService: AlbumsRestApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsDataSource {

    override suspend fun getAllUsers(): Result<List<UserModel>> {
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
}