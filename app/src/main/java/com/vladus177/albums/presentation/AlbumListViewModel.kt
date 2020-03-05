package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.domain.GetUsersListUseCase
import javax.inject.Inject

class AlbumListViewModel  @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase
) : ViewModel() {

    fun getAlbumList(userId: Long, forceUpdate: Boolean) {

    }
}