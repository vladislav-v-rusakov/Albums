package com.vladus177.albums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladus177.albums.data.local.UsersDao
import com.vladus177.albums.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
}