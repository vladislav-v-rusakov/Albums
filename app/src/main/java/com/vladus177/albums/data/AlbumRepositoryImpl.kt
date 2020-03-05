package vladus177.com.albums.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import vladus177.com.albums.di.ApplicationModule.AlbumsRemoteDataSource
import vladus177.com.albums.di.ApplicationModule.AlbumsLocalDataSource
import vladus177.com.albums.domain.model.UserModel
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    @AlbumsRemoteDataSource private val tasksRemoteDataSource: AlbumsDataSource,
    @AlbumsLocalDataSource private val tasksLocalDataSource: AlbumsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumsRepository {

    override suspend fun getAllUsers(forceUpdate: Boolean): Result<List<UserModel>> {
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