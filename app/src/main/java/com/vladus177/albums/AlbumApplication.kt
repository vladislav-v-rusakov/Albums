package com.vladus177.albums

import com.vladus177.albums.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class AlbumApplication : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}