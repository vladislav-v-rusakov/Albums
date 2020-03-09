package com.vladus177.albums.data.source

import com.nhaarman.mockitokotlin2.mock
import com.vladus177.albums.data.repository.UserLocal
import com.vladus177.albums.data.repository.UserRemote
import org.junit.Assert
import org.junit.Test

class UserDataSourceFactoryTest {

    private val remote = mock<UserRemote>()
    private val local = mock<UserLocal>()
    private val factory = UserDataSourceFactory(remote, local)

    @Test
    fun `whenGetRemote then UserRemote instance is not null`() {
        Assert.assertNotNull(factory.getRemote())
    }

    @Test
    fun `when getLocal, then UserLocal instance is not null`() {
        Assert.assertNotNull(factory.getLocal())
    }
}