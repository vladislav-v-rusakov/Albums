package com.vladus177.albums.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.vladus177.albums.domain.GetUserListUseCase
import com.vladus177.albums.domain.SetFavoriteUseCase
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.ui.mapper.UserViewMapper
import io.reactivex.observers.DisposableObserver
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor

class UserListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mapper = mock<UserViewMapper>()
    private val getDepositsUseCase = mock<GetUserListUseCase>()
    private val setUserFavoriteUseCase = mock<SetFavoriteUseCase>()
    private val viewModel = UserListViewModel(getDepositsUseCase, setUserFavoriteUseCase, mapper)
    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<UserModel>>>()

    @Test
    fun `whenLiveData thenReturnsNotNull`() {
        Assert.assertNotNull(viewModel.users)
    }


}