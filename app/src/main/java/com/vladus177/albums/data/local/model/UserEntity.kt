package com.vladus177.albums.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladus177.albums.data.Constants.USER_ALIAS
import com.vladus177.albums.data.Constants.USER_BS
import com.vladus177.albums.data.Constants.USER_CATCH_PHRASE
import com.vladus177.albums.data.Constants.USER_CITY
import com.vladus177.albums.data.Constants.USER_COMPANY_NAME
import com.vladus177.albums.data.Constants.USER_EMAIL
import com.vladus177.albums.data.Constants.USER_ID
import com.vladus177.albums.data.Constants.USER_IS_FAVORITE
import com.vladus177.albums.data.Constants.USER_LAT
import com.vladus177.albums.data.Constants.USER_LNG
import com.vladus177.albums.data.Constants.USER_NAME
import com.vladus177.albums.data.Constants.USER_PHONE
import com.vladus177.albums.data.Constants.USER_STREET
import com.vladus177.albums.data.Constants.USER_SUITE
import com.vladus177.albums.data.Constants.USER_WEBSITE
import com.vladus177.albums.data.Constants.USER_ZIPCODE

@Entity(tableName = "users")
data class UserEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = USER_ID) var id: Long? = 0,
    @ColumnInfo(name = USER_NAME) var userName: String? = "",
    @ColumnInfo(name = USER_ALIAS) var userAlias: String? = "",
    @ColumnInfo(name = USER_EMAIL) var userEmail: String? = "",
    @ColumnInfo(name = USER_STREET) var userStreet: String? = "",
    @ColumnInfo(name = USER_SUITE) var userSuite: String? = "",
    @ColumnInfo(name = USER_CITY) var userCity: String? = "",
    @ColumnInfo(name = USER_ZIPCODE) var userZipCode: String? = "",
    @ColumnInfo(name = USER_LAT) var userLat: String? = "",
    @ColumnInfo(name = USER_LNG) var userLng: String? = "",
    @ColumnInfo(name = USER_PHONE) var userPhone: String? = "",
    @ColumnInfo(name = USER_WEBSITE) var userWebsite: String? = "",
    @ColumnInfo(name = USER_COMPANY_NAME) var userCompanyName: String? = "",
    @ColumnInfo(name = USER_CATCH_PHRASE) var userCatchPhrase: String? = "",
    @ColumnInfo(name = USER_BS) var userBs: String? = "",
    @ColumnInfo(name = USER_IS_FAVORITE) var isFavorite: Boolean = false

) {
    val isUserFavorite
        get() = isFavorite
}
