package com.vladus177.albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladus177.albums.data.local.model.UserEntity
import io.reactivex.Completable

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>)

    @Query("UPDATE Users SET favorite = :favorite WHERE id = :userId")
    fun updateFavorite(userId: Long, favorite: Boolean)

    @Query("SELECT * FROM Users WHERE id = :userId")
    fun getUserById(userId: Long): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity): Completable


}