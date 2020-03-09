package com.vladus177.albums.ui.mapper


import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.factory.ModelFactory.makeUserModel
import com.vladus177.albums.ui.model.UserView
import org.junit.Assert
import org.junit.Test

class UserViewMapperTest {

    private val mapper = UserViewMapper()

    @Test
    fun `given UserModel, when fromDomainToView, then UserView`() {

        val model = makeUserModel()
        val view = with(mapper) { model.fromDomainToView() }

        Assert.assertEquals(model.id, view.id)
        Assert.assertEquals(model.name, view.name)
        Assert.assertEquals(model.username, view.username)
        Assert.assertEquals(model.email, view.email)
        assertAddress(view, model)
        Assert.assertEquals(model.phone, view.phone)
        Assert.assertEquals(model.website, view.website)
        assertCompany(view, model)
        Assert.assertEquals(model.isFavorite, view.isFavorite)
    }

    private fun assertAddress(view: UserView, model: UserModel) {
        model.address.let { address ->
            Assert.assertEquals(address?.street, view.address?.street)
            Assert.assertEquals(address?.suite, view.address?.suite)
            Assert.assertEquals(address?.zipcode, view.address?.zipcode)
            Assert.assertEquals(address?.city, view.address?.city)
            assertLocation(view, model)
        }
    }

    private fun assertLocation(view: UserView, model: UserModel) {
        model.address?.location.let { location ->
            Assert.assertEquals(location?.latitude, view.address?.location?.latitude)
            Assert.assertEquals(location?.longitude, view.address?.location?.longitude)
        }
    }

    private fun assertCompany(view: UserView, model: UserModel) {
        model.company.let { company ->
            Assert.assertEquals(company?.name, view.company?.name)
            Assert.assertEquals(company?.catchPhrase, view.company?.catchPhrase)
            Assert.assertEquals(company?.bs, view.company?.bs)
        }
    }
}