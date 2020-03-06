package com.vladus177.albums.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladus177.albums.data.Constants

@Entity(tableName = "images")
data class ImageEntity  @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = Constants.IMAGE_ID) var id: Long = 0,
    @ColumnInfo(name = Constants.IMAGE_ALBUM_ID) var albumId: Long = 0,
    @ColumnInfo(name = Constants.IMAGE_TITLE) var imageTitle: String = "",
    @ColumnInfo(name = Constants.IMAGE_URL) var url: String = "",
    @ColumnInfo(name = Constants.IMAGE_THUMBNAIL) var thumbnailUrl: String = "",
    @ColumnInfo(name = Constants.IMAGE_THUMBNAIL) var uriString: String = ""
)
