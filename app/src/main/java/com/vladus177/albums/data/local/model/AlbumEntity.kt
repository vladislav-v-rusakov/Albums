package com.vladus177.albums.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladus177.albums.data.Constants

@Entity(tableName = "albums")
data class AlbumEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = Constants.ALBUM_ID) var id: Long? = 0,
    @ColumnInfo(name = Constants.ALBUM_USER_ID) var userId: Long? = 0,
    @ColumnInfo(name = Constants.ALBUM_TITLE) var albumTitle: String? = ""

)