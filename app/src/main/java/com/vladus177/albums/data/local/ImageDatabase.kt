package com.vladus177.albums.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladus177.albums.data.local.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase(){

    abstract fun imageDao(): ImageDao


}