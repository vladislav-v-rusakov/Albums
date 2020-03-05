package com.vladus177.albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladus177.albums.data.local.model.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("UPDATE Users SET favorite = :favorite WHERE id = :userId")
    suspend fun updateFavorite(userId: Long, favorite: Boolean)


    @Query("SELECT * FROM Users WHERE id = :userId")
    suspend fun getTaskById(userId: Long): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)


}