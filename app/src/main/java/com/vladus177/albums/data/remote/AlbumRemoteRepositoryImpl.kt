package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.repository.AlbumRemote
import com.vladus177.albums.domain.model.AlbumModel
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val albumMapper: AlbumDataMapper
) :
    AlbumRemote {

    override fun getAlbumList(userId: Long): Observable<List<AlbumModel>> =
        restApi.getAlbumsByUserId(userId)
            .map { remoteUsers ->
                with(albumMapper) {
                    remoteUsers.map { it.fromDataToDomain() }
                }
            }
}