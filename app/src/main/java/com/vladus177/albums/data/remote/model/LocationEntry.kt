package vladus177.com.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import vladus177.com.albums.data.Constants.USER_LAT
import vladus177.com.albums.data.Constants.USER_LNG

data class LocationEntry(
    @SerializedName(USER_LAT) val latitude: String?,
    @SerializedName(USER_LNG) val longitude: String?
)