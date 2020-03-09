package com.vladus177.albums.factory

import com.vladus177.albums.data.local.model.UserEntity
import com.vladus177.albums.data.remote.model.AddressEntry
import com.vladus177.albums.data.remote.model.CompanyEntry
import com.vladus177.albums.data.remote.model.LocationEntry
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.AddressModel
import com.vladus177.albums.domain.model.CompanyModel
import com.vladus177.albums.domain.model.LocationModel
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.ui.model.AddressView
import com.vladus177.albums.ui.model.CompanyView
import com.vladus177.albums.ui.model.LocationView
import com.vladus177.albums.ui.model.UserView

object ModelFactory {

    fun makeUserEntry() = UserEntry(
        id = RandomFactory.generateLong(),
        name = RandomFactory.generateString(),
        username = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        address = makeAddressEntry(),
        phone = RandomFactory.generateString(),
        website = RandomFactory.generateString(),
        company = makeCompanyEntry()
    )

    private fun makeAddressEntry() = AddressEntry(
        street = RandomFactory.generateString(),
        suite = RandomFactory.generateString(),
        zipcode = RandomFactory.generateString(),
        city = RandomFactory.generateString(),
        location = makeLocationEntry()

    )

    private fun makeCompanyEntry() = CompanyEntry(
        name = RandomFactory.generateString(),
        catchPhrase = RandomFactory.generateString(),
        bs = RandomFactory.generateString()
    )

    private fun makeLocationEntry() = LocationEntry(
        latitude = RandomFactory.generateString(),
        longitude = RandomFactory.generateString()
    )


    fun makeUserEntity() = UserEntity(
        id = RandomFactory.generateLong(),
        userName = RandomFactory.generateString(),
        userAlias = RandomFactory.generateString(),
        userEmail = RandomFactory.generateString(),
        userStreet = RandomFactory.generateString(),
        userCity = RandomFactory.generateString(),
        userSuite = RandomFactory.generateString(),
        userZipCode = RandomFactory.generateString(),
        userLat = RandomFactory.generateString(),
        userLng = RandomFactory.generateString(),
        userPhone = RandomFactory.generateString(),
        userWebsite = RandomFactory.generateString(),
        userCompanyName = RandomFactory.generateString(),
        userCatchPhrase = RandomFactory.generateString(),
        userBs = RandomFactory.generateString()
    )

    fun makeUserModel() = UserModel(
        id = RandomFactory.generateLong(),
        name = RandomFactory.generateString(),
        username = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        address = makeAddressModel(),
        phone = RandomFactory.generateString(),
        website = RandomFactory.generateString(),
        company = makeCompanyModel(),
        isFavorite = RandomFactory.generateBoolean()
    )

    private fun makeAddressModel() = AddressModel(
        street = RandomFactory.generateString(),
        suite = RandomFactory.generateString(),
        zipcode = RandomFactory.generateString(),
        city = RandomFactory.generateString(),
        location = makeLocationModel()

    )

    private fun makeCompanyModel() = CompanyModel(
        name = RandomFactory.generateString(),
        catchPhrase = RandomFactory.generateString(),
        bs = RandomFactory.generateString()
    )

    private fun makeLocationModel() = LocationModel(
        latitude = RandomFactory.generateString(),
        longitude = RandomFactory.generateString()
    )

    fun makeUserView() = UserView(
        id = RandomFactory.generateLong(),
        name = RandomFactory.generateString(),
        username = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        address = makeAddressView(),
        phone = RandomFactory.generateString(),
        website = RandomFactory.generateString(),
        company = makeCompanyView(),
        isFavorite = RandomFactory.generateBoolean()
    )

    private fun makeAddressView() = AddressView(
        street = RandomFactory.generateString(),
        suite = RandomFactory.generateString(),
        zipcode = RandomFactory.generateString(),
        city = RandomFactory.generateString(),
        location = makeLocationView(),
        fullAddress = RandomFactory.generateString()
    )

    private fun makeCompanyView() = CompanyView(
        name = RandomFactory.generateString(),
        catchPhrase = RandomFactory.generateString(),
        bs = RandomFactory.generateString()
    )

    private fun makeLocationView() = LocationView(
        latitude = RandomFactory.generateString(),
        longitude = RandomFactory.generateString()
    )


    fun makeUserModelList(count: Int): List<UserModel> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0..count).map { makeUserModel() }
        }
    }

    fun makeUserEntryList(count: Int): List<UserEntry> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0..count).map { makeUserEntry() }
        }
    }

}