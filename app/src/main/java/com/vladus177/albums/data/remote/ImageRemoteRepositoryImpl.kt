package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.ImageDataMapper
import com.vladus177.albums.data.repository.ImageRemote
import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable
import javax.inject.Inject

class ImageRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val imageMapper: ImageDataMapper
) :
    ImageRemote {

    override fun getImageList(albumId: Long): Observable<List<ImageModel>> =
        restApi.getImagesByAlbumId(albumId)
            .map { remoteUsers ->
                with(imageMapper) {
                    remoteUsers.map { it.fromDataToDomain() }
                }
            }
}
