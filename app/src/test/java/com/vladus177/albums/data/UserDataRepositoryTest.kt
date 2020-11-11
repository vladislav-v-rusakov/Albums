package com.vladus177.albums.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vladus177.albums.data.local.model.UserEntity
import com.vladus177.albums.data.repository.AlbumRemoteSource
import com.vladus177.albums.data.repository.AlbumsDataRepository
import com.vladus177.albums.data.repository.AlbumsLocalSource
import com.vladus177.albums.factory.ModelFactory.makeUserEntity
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Ignore
import org.junit.Test

class UserDataRepositoryTest {

    private val remote = mockk<AlbumRemoteSource>()
    private val local = mock<AlbumsLocalSource>()

    private val dataRepository = mock<AlbumsDataRepository>()


    @Test
    fun `givenUser whenGetUsers thenComplets`() {
        val userModel = makeUserEntity()
        val userList = listOf(userModel)
        stubGetUsers(Observable.just(userList))
        val testObserver = dataRepository.getUserList(false).test()
        testObserver.assertComplete()
    }

    @Ignore
    @Test
    fun `givenUser whenGetUsers thenReturnData`() {
        val userModel = makeUserEntity()
        val userList = listOf(userModel)
        stubGetUsers(Observable.just(userList))
        val testObserver = dataRepository.getUserList(false).test()
        //testObserver.assertValue(userList)
    }

    @Test
    fun `givenUser whenUpdateDB thenReturnComplete`() {
        stubUpdateUser(Completable.complete())
        val testObserver = dataRepository.setFavoriteUser(1, true).test()
        testObserver.assertComplete()
    }

    private fun stubGetUsers(observable: Observable<List<UserEntity>>) {
        whenever(local.getUserList()).thenReturn(observable)
    }

    private fun stubUpdateUser(completable: Completable) {
        whenever(local.setFavoriteUser(1, true)).thenReturn(completable)
    }
}