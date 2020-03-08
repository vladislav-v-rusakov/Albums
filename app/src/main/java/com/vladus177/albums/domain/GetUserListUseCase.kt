package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Observable
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {
    fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>> =
        repository.getUserList(forceUpdate)
}