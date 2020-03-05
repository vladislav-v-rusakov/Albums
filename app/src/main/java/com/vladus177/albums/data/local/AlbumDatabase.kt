package com.vladus177.albums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladus177.albums.data.local.model.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumDao

}