package com.vladus177.albums.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.factory.ModelFactory.makeUserModelList
import io.reactivex.Observable
import org.junit.Test

class GetUserListUseCaseTest {

    private val repository = mock<AlbumRepository>()
    private val getDepositsUseCase = GetUserListUseCase(repository)

    @Test
    fun `givenUsers getUserList_thenComplete`() {
        stubGetUsers(Observable.just(makeUserModelList(5)))
        val testObserver = getDepositsUseCase.getUserList(true).test()
        testObserver.assertComplete()
    }

    @Test
    fun `givenUserList getUserList thenReturnData`() {
        val deposits = makeUserModelList(5)
        stubGetUsers(Observable.just(deposits))
        val testObserver = getDepositsUseCase.getUserList(true).test()
        testObserver.assertValue(deposits)
    }

    private fun stubGetUsers(observable: Observable<List<UserModel>>) {
        whenever(repository.getUserList(true)).thenReturn(observable)
    }

}