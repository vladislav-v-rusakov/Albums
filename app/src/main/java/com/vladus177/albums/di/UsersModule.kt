package com.vladus177.albums.di

import androidx.lifecycle.ViewModel
import com.vladus177.albums.presentation.UsersListViewModel
import com.vladus177.albums.ui.fragment.UserListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
class UsersModul {
    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun tasksFragment(): UserListFragment

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindViewModel(viewmodel: UsersListViewModel): ViewModel
}