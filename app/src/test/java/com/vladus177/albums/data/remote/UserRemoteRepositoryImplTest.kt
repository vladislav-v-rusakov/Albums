package com.vladus177.albums.data.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.factory.ModelFactory.makeUserEntry
import com.vladus177.albums.factory.ModelFactory.makeUserEntryList
import com.vladus177.albums.factory.ModelFactory.makeUserModel
import io.mockk.every
import io.reactivex.Observable
import org.junit.Ignore
import org.junit.Test

class UserRemoteRepositoryImplTest {

    private val restApi = mock<AlbumsRestApi>()
    private val mapper = mock<UserDataMapper>()
    private val userRemoteRepositoryImpl = UserRemoteRepositoryImpl(restApi, mapper)

    @Test
    fun `givenUserEntry whenGetUsers thenComplete`() {
        stubRestApiGetUsers(Observable.just(makeUserEntryList(10)))
        val testObserver = userRemoteRepositoryImpl.getUserList().test()
        testObserver.assertComplete()
    }

    @Ignore("https://github.com/mockk/mockk/issues/399")
    @Test
    fun `givenEntry whenGetGetUsers thenReturnData`() {
        val remote = makeUserEntry()
        val domain = makeUserModel()
        stubRestApiGetUsers(Observable.just(listOf(remote)))
        stubUserMapper(domain, remote)

        val testObserver = userRemoteRepositoryImpl.getUserList().test()

        testObserver.assertValue(listOf(domain))
    }

    private fun stubUserMapper(model: UserModel, entry: UserEntry) {
        with(mapper) {
            every { entry.fromDataToDomain() } returns model
        }
    }

    private fun stubRestApiGetUsers(observable: Observable<List<UserEntry>>) {
        whenever(restApi.getAllUsers()).thenReturn(observable)
    }
}