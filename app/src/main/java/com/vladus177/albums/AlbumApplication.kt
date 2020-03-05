package vladus177.com.albums

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import vladus177.com.albums.di.DaggerApplicationComponent

open class AlbumApplication : DaggerApplication() {

     override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

         return DaggerApplicationComponent.factory().create(applicationContext)
     }
}