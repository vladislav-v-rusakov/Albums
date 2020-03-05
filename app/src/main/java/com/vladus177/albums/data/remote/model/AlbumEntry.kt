package com.vladus177.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladus177.albums.data.Constants

data class AlbumEntry (
    @SerializedName(Constants.ALBUM_USER_ID) val userId: Long?,
    @SerializedName(Constants.ALBUM_ID) val id: Long?,
    @SerializedName(Constants.ALBUM_TITLE) val title:String?
    )