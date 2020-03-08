package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.data.repository.UserRemote
import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Observable
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val usserMapper: UserDataMapper
) : UserRemote {

    override fun getUserList(): Observable<List<UserModel>> = restApi.getAllUsers()
        .map { remoteUsers ->
            with(usserMapper) {
                remoteUsers.map { it.fromDataToDomain() }
            }
        }
}