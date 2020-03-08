package com.vladus177.albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladus177.albums.data.local.model.ImageEntity

@Dao
interface ImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): List<ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<ImageEntity>)

}