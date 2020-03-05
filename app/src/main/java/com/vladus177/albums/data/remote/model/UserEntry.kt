package vladus177.com.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import vladus177.com.albums.data.Constants.USER_ADDRESS
import vladus177.com.albums.data.Constants.USER_ALIAS
import vladus177.com.albums.data.Constants.USER_COMPANY
import vladus177.com.albums.data.Constants.USER_EMAIL
import vladus177.com.albums.data.Constants.USER_ID
import vladus177.com.albums.data.Constants.USER_NAME
import vladus177.com.albums.data.Constants.USER_PHONE
import vladus177.com.albums.data.Constants.USER_WEBSITE


data class UserEntry(
    @SerializedName(USER_ID) val id: Long?,
    @SerializedName(USER_NAME) val name: String?,
    @SerializedName(USER_ALIAS) val username: String?,
    @SerializedName(USER_EMAIL) val email: String?,
    @SerializedName(USER_ADDRESS) val address: AddressEntry?,
    @SerializedName(USER_PHONE) val phone: String?,
    @SerializedName(USER_WEBSITE) val website: String?,
    @SerializedName(USER_COMPANY) val company: CompanyEntry?
)