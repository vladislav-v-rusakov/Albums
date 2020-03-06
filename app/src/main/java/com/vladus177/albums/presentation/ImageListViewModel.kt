package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetImageListUseCase
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.model.ImagesByAlbumIdParam
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModel() {

    val imageLiveData = LiveResult<List<ImageModel>>()

    fun getImagesListByAlbumId(albumId: Long, forceUpdate: Boolean) {
        getImageListUseCase.execute(imageLiveData, ImagesByAlbumIdParam(forceUpdate, albumId))
    }
}