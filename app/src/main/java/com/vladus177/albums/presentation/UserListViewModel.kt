package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetUserListUseCase
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    val userLiveData = LiveResult<List<UserModel>>()

    fun loadUserList(forceUpdate: Boolean) {
        getUserListUseCase.execute(
            liveData = userLiveData,
            params = forceUpdate
        )
    }

    fun setFavorite(userId: Long?, favorite: Boolean) {

    }
}