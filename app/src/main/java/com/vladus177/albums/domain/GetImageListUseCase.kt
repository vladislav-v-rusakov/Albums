package com.vladus177.albums.domain


import com.vladus177.albums.domain.model.ImageModel
import io.reactivex.Observable
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    fun getImageList(albumId: Long, forceUpdate: Boolean): Observable<List<ImageModel>> =
        repository.getImageList(albumId, forceUpdate)
}
