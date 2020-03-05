package com.vladus177.albums

import com.vladus177.albums.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


open class AlbumApplication : DaggerApplication() {

     override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

         return DaggerApplicationComponent.factory().create(applicationContext)
     }
}