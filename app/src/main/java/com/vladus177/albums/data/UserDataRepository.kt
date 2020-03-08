package com.vladus177.albums.data


import com.vladus177.albums.data.source.UserDataSourceFactory
import com.vladus177.albums.domain.UserRepository

import io.reactivex.Observable

import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.schedulers.Schedulers
import com.vladus177.albums.domain.model.UserModel


@Singleton
class UserDataRepository @Inject constructor(
    private val factory: UserDataSourceFactory
) : UserRepository {


    override fun getUserList(forceUpdate: Boolean): Observable<List<UserModel>> {
        return if (forceUpdate) {
            Observable.mergeDelayError(
                factory.getRemote().getUserList().doOnNext { users ->
                    insertAll(users)
                }.subscribeOn(Schedulers.io()),
                factory.getLocal().getUserList().subscribeOn(Schedulers.io())
            )

        } else {
            factory.getLocal().getUserList()
        }
    }

    override fun insertUser(user: UserModel) {
        factory.getLocal().insertUser(user)
    }

    override fun insertAll(users: List<UserModel>) = factory.getLocal().insertAll(users)
}