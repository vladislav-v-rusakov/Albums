package com.vladus177.albums.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vladus177.albums.data.repository.UserLocal
import com.vladus177.albums.data.repository.UserRemote
import com.vladus177.albums.data.source.UserDataSourceFactory
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.factory.ModelFactory.makeUserModel
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class UserDataRepositoryTest {

    private val factory = mock<UserDataSourceFactory>()
    private val remote = mockk<UserRemote>()
    private val local = mock<UserLocal>()

    private val userDataRepository = UserDataRepository(factory)

    @Before
    fun setUp() {
        stubFactoryGetRemote()
        stubFactoryGetLocal()
    }

    private fun stubFactoryGetRemote() {
        whenever(factory.getRemote()).thenReturn(remote)
    }

    private fun stubFactoryGetLocal() {
        whenever(factory.getLocal()).thenReturn(local)
    }

    @Test
    fun `givenUser whenGetUsers thenCompletes`() {
        val userModel = makeUserModel()
        val userList = listOf(userModel)
        stubGetUsers(Observable.just(userList))
        val testObserver = userDataRepository.getUserList(false).test()
        testObserver.assertComplete()
    }

    @Test
    fun `givenUser whenGetUsers thenReturnData`() {
        val userModel = makeUserModel()
        val userList = listOf(userModel)
        stubGetUsers(Observable.just(userList))
        val testObserver = userDataRepository.getUserList(false).test()
        testObserver.assertValue(userList)
    }

    @Test
    fun `givenUser whenUpdateDB thenReturnComplete`() {
        stubUpdateUser(Completable.complete())
        val testObserver = userDataRepository.setFavoriteUser(1, true).test()
        testObserver.assertComplete()
    }

    private fun stubGetUsers(observable: Observable<List<UserModel>>) {
        whenever(local.getUserList()).thenReturn(observable)
    }

    private fun stubUpdateUser(completable: Completable) {
        whenever(local.setFavoriteUser(1, true)).thenReturn(completable)
    }
}