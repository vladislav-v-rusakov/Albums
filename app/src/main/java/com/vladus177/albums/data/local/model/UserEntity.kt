package vladus177.com.albums.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import vladus177.com.albums.data.Constants.USER_ALIAS
import vladus177.com.albums.data.Constants.USER_BS
import vladus177.com.albums.data.Constants.USER_CATCH_PHRASE
import vladus177.com.albums.data.Constants.USER_CITY
import vladus177.com.albums.data.Constants.USER_COMPANY_NAME
import vladus177.com.albums.data.Constants.USER_EMAIL
import vladus177.com.albums.data.Constants.USER_ID
import vladus177.com.albums.data.Constants.USER_IS_FAVORITE
import vladus177.com.albums.data.Constants.USER_LAT
import vladus177.com.albums.data.Constants.USER_LNG
import vladus177.com.albums.data.Constants.USER_NAME
import vladus177.com.albums.data.Constants.USER_PHONE
import vladus177.com.albums.data.Constants.USER_STREET
import vladus177.com.albums.data.Constants.USER_SUITE
import vladus177.com.albums.data.Constants.USER_WEBSITE
import vladus177.com.albums.data.Constants.USER_ZIPCODE

@Entity(tableName = "users")
data class UserEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = USER_ID) var id: Long = 0,
    @ColumnInfo(name = USER_NAME) var userName: String = "",
    @ColumnInfo(name = USER_ALIAS) var userAlias: String = "",
    @ColumnInfo(name = USER_EMAIL) var userEmail: String = "",
    @ColumnInfo(name = USER_STREET) var userStreet: String = "",
    @ColumnInfo(name = USER_SUITE) var userSuite: String = "",
    @ColumnInfo(name = USER_CITY) var userCity: String = "",
    @ColumnInfo(name = USER_ZIPCODE) var userZipCode: String = "",
    @ColumnInfo(name = USER_LAT) var userLat: String = "",
    @ColumnInfo(name = USER_LNG) var userLng: String = "",
    @ColumnInfo(name = USER_PHONE) var userPhone: String = "",
    @ColumnInfo(name = USER_WEBSITE) var userWebsite: String = "",
    @ColumnInfo(name = USER_COMPANY_NAME) var userCompanyName: String = "",
    @ColumnInfo(name = USER_CATCH_PHRASE) var userCatchPhrase: String = "",
    @ColumnInfo(name = USER_BS) var userBs: String = "",
    @ColumnInfo(name = USER_IS_FAVORITE) var isFavorite: Boolean = false

) {
    val isUserFavorite
        get() = isFavorite
}
