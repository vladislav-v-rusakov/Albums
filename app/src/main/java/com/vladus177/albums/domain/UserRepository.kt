package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {

    fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>>

    fun setFavoriteUser(userId: Long, favorite: Boolean): Completable

    fun insertAll(users: List<UserModel>)
}
