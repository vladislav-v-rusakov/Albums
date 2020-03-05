package com.vladus177.albums.ui.mapper

import com.vladus177.albums.domain.model.AddressModel
import com.vladus177.albums.domain.model.CompanyModel
import com.vladus177.albums.domain.model.LocationModel
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.ui.model.AddressView
import com.vladus177.albums.ui.model.CompanyView
import com.vladus177.albums.ui.model.LocationView
import com.vladus177.albums.ui.model.UserView
import javax.inject.Inject

class UserViewMapper @Inject constructor() {

    fun UserModel.fromDomainToView() = UserView(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.fromDomainToView(),
        phone = phone,
        website = website,
        company = company?.fromDomainToView(),
        isFavorite = isFavorite
    )

    private fun AddressModel.fromDomainToView() = AddressView(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        location = location?.fromDomainToView(),
        fullAddress = "$zipcode, $city, $street, $suite"
    )

    private fun LocationModel.fromDomainToView() = LocationView(
        latitude = latitude,
        longitude = longitude
    )

    private fun CompanyModel.fromDomainToView() = CompanyView(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}