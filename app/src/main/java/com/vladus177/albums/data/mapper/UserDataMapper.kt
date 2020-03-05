package com.vladus177.albums.data.mapper

import com.vladus177.albums.data.remote.model.AddressEntry
import com.vladus177.albums.data.remote.model.CompanyEntry
import com.vladus177.albums.data.remote.model.LocationEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.AddressModel
import com.vladus177.albums.domain.model.CompanyModel
import com.vladus177.albums.domain.model.LocationModel
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject

class UserDataMapper @Inject constructor() {

    fun UserEntry.fromDataToDomain() = UserModel(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.fromDataToDomain(),
        phone = phone,
        website = website,
        company = company?.fromDataToDomain(),
        isFavorite = false
    )

    fun AddressEntry.fromDataToDomain() = AddressModel(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        location = location?.fromDataToDomain()
    )

    fun LocationEntry.fromDataToDomain() = LocationModel(
        latitude = latitude,
        longitude = longitude
    )

    fun CompanyEntry.fromDataToDomain() = CompanyModel(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}