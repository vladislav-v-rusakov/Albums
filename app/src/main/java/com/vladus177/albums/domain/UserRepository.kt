package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface UserRepository {

    fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>>

    fun insertUser(user: UserModel)

    fun insertAll(users: List<UserModel>)
}
