package com.vladus177.albums.data.local

import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.data.repository.UserLocal
import com.vladus177.albums.domain.model.UserModel
import io.reactivex.Observable
import javax.inject.Inject


class UserLocalRepositoryImpl @Inject constructor(
    private val userDao: UsersDao, private val userMapper: UserDataMapper
) : UserLocal {

    override fun insertUser(user: UserModel) {
        val user1 = with(userMapper) { user.fromDomaimToEntity() }
        userDao.insertUser(user1)
    }

    override fun insertAll(users: List<UserModel>) {
        userDao.insertAll(users.map { with(userMapper) { it.fromDomaimToEntity() } })
    }

    override fun getUserList(): Observable<List<UserModel>> {
        return Observable.fromCallable<List<UserModel>> {
            userDao.getAllUsers().map { with(userMapper) { it.fromEntityToDomain() } }
        }
    }
}