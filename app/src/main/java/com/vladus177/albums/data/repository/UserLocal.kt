package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Completable
import io.reactivex.Observable

interface UserLocal {

    fun getUserList(): Observable<List<UserModel>>

    fun insertAll(users: List<UserModel>)

    fun setFavoriteUser(userId: Long, favorite: Boolean) : Completable
}