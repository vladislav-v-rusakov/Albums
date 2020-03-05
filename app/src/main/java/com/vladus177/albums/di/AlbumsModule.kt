package com.vladus177.albums.di

import androidx.lifecycle.ViewModel
import com.vladus177.albums.presentation.AlbumListViewModel
import com.vladus177.albums.ui.fragment.AlbumListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AlbumsModule {
    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun albumsFragment(): AlbumListFragment

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel::class)
    abstract fun bindViewModel(viewmodel: AlbumListViewModel): ViewModel
}