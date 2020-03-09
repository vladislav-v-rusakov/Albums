package com.vladus177.albums.ui.fragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vladus177.albums.R
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.ResourceState
import com.vladus177.albums.factory.ViewModelUtil
import com.vladus177.albums.presentation.UserListViewModel
import com.vladus177.albums.ui.model.UserView
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<TestUserListFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer()
    }

    @Ignore("IllegalArgumentException: No injector factory bound")
    @Test
    fun `givenLoadingState whenPost thenDisplayLoading`() {

        val viewState = Resource(ResourceState.LOADING, null, "")
        fragmentScenario.onFragment {
            it.userLiveData.postValue(viewState)
        }

        onView(ViewMatchers.withId(R.id.pb_loading))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Ignore("IllegalArgumentException: No injector factory bound")
    @Test
    fun `givenErrorState whenPost thenDisplayError`() {

        val viewState = Resource(ResourceState.ERROR, null, "")
        fragmentScenario.onFragment {
            it.userLiveData.postValue(viewState)
        }

        onView(ViewMatchers.withId(R.id.di_user_info))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    class TestUserListFragment : UserListFragment() {
        private val viewModelFake = mock<UserListViewModel>()

        val userLiveData: MutableLiveData<Resource<List<UserView>>> = MutableLiveData()


        override fun onActivityCreated(savedInstanceState: Bundle?) {
            stubViewModel()
            this.viewModelFactory = ViewModelUtil.createFor(viewModelFake)
        }

        private fun stubViewModel() {
            whenever(viewModelFake.users).thenReturn(userLiveData)
        }

    }
}