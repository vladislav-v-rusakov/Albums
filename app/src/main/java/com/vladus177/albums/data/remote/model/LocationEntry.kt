package com.vladus177.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladus177.albums.data.Constants.USER_LAT
import com.vladus177.albums.data.Constants.USER_LNG

data class LocationEntry(
    @SerializedName(USER_LAT) val latitude: String?,
    @SerializedName(USER_LNG) val longitude: String?
)