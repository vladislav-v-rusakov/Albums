package vladus177.com.albums.domain

import vladus177.com.albums.data.AlbumsRepository
import vladus177.com.albums.data.Result
import vladus177.com.albums.domain.model.UserModel
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val albumsRepository: AlbumsRepository) {
    suspend operator fun invoke(forceUpdate: Boolean): Result<List<UserModel>> =
        albumsRepository.getAllUsers(forceUpdate)
}