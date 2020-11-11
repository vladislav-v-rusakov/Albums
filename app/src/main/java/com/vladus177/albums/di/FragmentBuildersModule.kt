package com.vladus177.albums.di

import com.vladus177.albums.ui.fragment.AlbumListFragment
import com.vladus177.albums.ui.fragment.ImageListFragment
import com.vladus177.albums.ui.fragment.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeImagesFragment(): ImageListFragment

    @ContributesAndroidInjector
    abstract fun contributeUsersFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun contributeAlbumsFragment(): AlbumListFragment

}