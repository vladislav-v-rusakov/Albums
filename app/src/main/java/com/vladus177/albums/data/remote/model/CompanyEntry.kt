package com.vladus177.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladus177.albums.data.Constants.USER_BS
import com.vladus177.albums.data.Constants.USER_CATCH_PHRASE
import com.vladus177.albums.data.Constants.USER_NAME

data class CompanyEntry(
    @SerializedName(USER_NAME) val name: String?,
    @SerializedName(USER_CATCH_PHRASE) val catchPhrase: String?,
    @SerializedName(USER_BS) val bs: String?
)