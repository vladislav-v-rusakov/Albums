package com.vladus177.albums.domain

import com.vladus177.albums.common.ResultUseCase
import com.vladus177.albums.data.AlbumsRepository
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.AlbumsByIdParam
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(private val albumsRepository: AlbumsRepository) : ResultUseCase<AlbumsByIdParam, List<AlbumModel>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: AlbumsByIdParam): List<AlbumModel>? {
        return albumsRepository.getAlbumsByUserId(params.forceUpdate, params.userId)
    }
}