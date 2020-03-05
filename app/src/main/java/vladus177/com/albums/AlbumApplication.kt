package vladus177.com.albums

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AlbumApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.factory().create(applicationContext)
    }

}