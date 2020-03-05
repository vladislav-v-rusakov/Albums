package com.vladus177.albums.domain

import com.vladus177.albums.common.ResultUseCase
import com.vladus177.albums.data.AlbumsRepository
import com.vladus177.albums.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/*
class GetUsersListUseCase @Inject constructor(private val albumsRepository: AlbumsRepository) {

    suspend operator fun invoke(forceUpdate: Boolean): Result<List<UserModel>> =
        albumsRepository.getAllUsers(forceUpdate)
}*/


class GetUsersListUseCase @Inject constructor(private val albumsRepository: AlbumsRepository) : ResultUseCase<Boolean, List<UserModel>>(
backgroundContext = Dispatchers.IO,
foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: Boolean): List<UserModel>? {
        return albumsRepository.getAllUsers(params)
    }
}