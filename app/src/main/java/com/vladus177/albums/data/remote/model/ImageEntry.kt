package com.vladus177.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladus177.albums.data.Constants

data class ImageEntry(
    @SerializedName(Constants.IMAGE_ALBUM_ID) val albumId: Long?,
    @SerializedName(Constants.IMAGE_ID) val id: Long?,
    @SerializedName(Constants.IMAGE_TITLE) val title: String?,
    @SerializedName(Constants.IMAGE_URL) val url: String?,
    @SerializedName(Constants.IMAGE_THUMBNAIL) val thumbnail: String?
)