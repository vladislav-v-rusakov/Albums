package com.vladus177.albums.di

import androidx.lifecycle.ViewModel
import com.vladus177.albums.presentation.ImageListViewModel
import com.vladus177.albums.ui.fragment.ImageListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ImagesModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun imagesFragment(): ImageListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    abstract fun bindViewModel(viewmodel: ImageListViewModel): ViewModel
}