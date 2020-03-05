package vladus177.com.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import vladus177.com.albums.data.Constants.USER_BS
import vladus177.com.albums.data.Constants.USER_CATCH_PHRASE
import vladus177.com.albums.data.Constants.USER_NAME

data class CompanyEntry(
    @SerializedName(USER_NAME) val name: String?,
    @SerializedName(USER_CATCH_PHRASE) val catchPhrase: String?,
    @SerializedName(USER_BS) val bs: String?
)