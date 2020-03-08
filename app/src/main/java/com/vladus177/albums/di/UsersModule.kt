package com.vladus177.albums.di

import androidx.lifecycle.ViewModel
import com.vladus177.albums.presentation.UserListViewModel
import com.vladus177.albums.ui.fragment.UserListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UsersModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun tasksFragment(): UserListFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindViewModel(viewmodel: UserListViewModel): ViewModel
}