package com.vladus177.albums.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladus177.albums.data.local.model.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM Albums")
    suspend fun getAllAlbums(): List<AlbumEntity>

    @Query("SELECT * FROM Albums WHERE userId = :userId")
    suspend fun getAlbumsByUserId(userId: Long): List<AlbumEntity>

    @Query("SELECT * FROM Albums WHERE id = :albumId")
    suspend fun getAlbumById(albumId: Long): AlbumEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

}