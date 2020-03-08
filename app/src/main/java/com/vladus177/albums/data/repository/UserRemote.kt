package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Observable

interface UserRemote {

    fun getUserList(): Observable<List<UserModel>>
}