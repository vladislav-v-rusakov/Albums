package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetUsersListUseCase
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.ui.mapper.UserViewMapper
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val mapper: UserViewMapper
) : ViewModel() {

    val userLiveData = LiveResult<List<UserModel>>()

    fun loadUserList(forceUpdate: Boolean) {

        getUsersListUseCase.execute(
            liveData = userLiveData,
            params = forceUpdate
        )

    }
}