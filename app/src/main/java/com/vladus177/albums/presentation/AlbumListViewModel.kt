package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetAlbumListUseCase
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.AlbumsByIdParam
import javax.inject.Inject

class AlbumListViewModel  @Inject constructor(
    private val getAlbumListUseCase: GetAlbumListUseCase
) : ViewModel() {

    val albumLiveData = LiveResult<List<AlbumModel>>()

    fun getAlbumList(userId: Long, forceUpdate: Boolean) {
        getAlbumListUseCase.execute(albumLiveData, AlbumsByIdParam(forceUpdate, userId))
    }
}