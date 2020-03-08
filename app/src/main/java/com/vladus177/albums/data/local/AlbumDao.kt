package com.vladus177.albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladus177.albums.data.local.model.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM Albums")
    fun getAllAlbums(): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: List<AlbumEntity>)

    @Query("SELECT * FROM Albums WHERE userId = :userId")
    fun getAlbumsByUserId(userId: Long): List<AlbumEntity>

    @Query("SELECT * FROM Albums WHERE id = :albumId")
    fun getAlbumById(albumId: Long): AlbumEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: AlbumEntity)

}