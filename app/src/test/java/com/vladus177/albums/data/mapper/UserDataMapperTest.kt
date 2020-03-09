package com.vladus177.albums.data.mapper

import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.factory.ModelFactory
import org.junit.Assert
import org.junit.Test

class UserDataMapperTest {

    private val mapper = UserDataMapper()

    @Test
    fun `given UserModel, when fromDomainToEntity, then return UserEntity`() {

        val model = ModelFactory.makeUserModel()
        val entity = with(mapper) { model.fromDomainToEntity() }

        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.userName, model.name)
        Assert.assertEquals(entity.userAlias, model.username)
        Assert.assertEquals(entity.userEmail, model.email)
        Assert.assertEquals(entity.userStreet, model.address?.street)
        Assert.assertEquals(entity.userSuite, model.address?.suite)
        Assert.assertEquals(entity.userCity, model.address?.city)
        Assert.assertEquals(entity.userZipCode, model.address?.zipcode)
        Assert.assertEquals(entity.userLat, model.address?.location?.latitude)
        Assert.assertEquals(entity.userLng, model.address?.location?.longitude)
        Assert.assertEquals(entity.userPhone, model.phone)
        Assert.assertEquals(entity.userWebsite, model.website)
        Assert.assertEquals(entity.userCompanyName, model.company?.name)
        Assert.assertEquals(entity.userCatchPhrase, model.company?.catchPhrase)
        Assert.assertEquals(entity.userBs, model.company?.bs)
    }

    @Test
    fun `given UserEntity, when fromEntityToDomain, then return UserModel`() {

        val entity = ModelFactory.makeUserEntity()
        val model = with(mapper) { entity.fromEntityToDomain() }

        Assert.assertEquals(model.id, entity.id)
        Assert.assertEquals(model.name, entity.userName)
        Assert.assertEquals(model.username, entity.userAlias)
        Assert.assertEquals(model.email, entity.userEmail)
        Assert.assertEquals(model.address?.street, entity.userStreet)
        Assert.assertEquals(model.address?.suite, entity.userSuite)
        Assert.assertEquals(model.address?.city, entity.userCity)
        Assert.assertEquals(model.address?.zipcode, entity.userZipCode)
        Assert.assertEquals(model.address?.location?.latitude, entity.userLat)
        Assert.assertEquals(model.address?.location?.longitude, entity.userLng)
        Assert.assertEquals(model.phone, entity.userPhone)
        Assert.assertEquals(model.website, entity.userWebsite)
        Assert.assertEquals(model.company?.name, entity.userCompanyName)
        Assert.assertEquals(model.company?.catchPhrase, entity.userCatchPhrase)
        Assert.assertEquals(model.company?.bs, entity.userBs)

    }

    @Test
    fun `given UserEntry, when fromEntryToDomain, then return UserModel`() {

        val entry = ModelFactory.makeUserEntry()
        val model = with(mapper) { entry.fromDataToDomain() }

        Assert.assertEquals(entry.id, model.id)
        Assert.assertEquals(entry.name, model.name)
        Assert.assertEquals(entry.username, model.username)
        Assert.assertEquals(entry.email, model.email)
        assertAddress(entry, model)
        Assert.assertEquals(entry.phone, model.phone)
        Assert.assertEquals(entry.website, model.website)
        assertCompany(entry, model)

    }


    private fun assertAddress(entry: UserEntry, model: UserModel) {
        entry.address.let { address ->
            Assert.assertEquals(address?.street, model.address?.street)
            Assert.assertEquals(address?.suite, model.address?.suite)
            Assert.assertEquals(address?.zipcode, model.address?.zipcode)
            Assert.assertEquals(address?.city, model.address?.city)
            assertLocation(entry, model)
        }
    }

    private fun assertLocation(entry: UserEntry, model: UserModel) {
        entry.address?.location.let { location ->
            Assert.assertEquals(location?.latitude, model.address?.location?.latitude)
            Assert.assertEquals(location?.longitude, model.address?.location?.longitude)
        }
    }

    private fun assertCompany(entry: UserEntry, model: UserModel) {
        entry.company.let { company ->
            Assert.assertEquals(company?.name, model.company?.name)
            Assert.assertEquals(company?.catchPhrase, model.company?.catchPhrase)
            Assert.assertEquals(company?.bs, model.company?.bs)
        }
    }
}