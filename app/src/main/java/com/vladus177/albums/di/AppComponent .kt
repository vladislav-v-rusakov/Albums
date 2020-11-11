package com.vladus177.albums.di

import android.app.Application
import com.vladus177.albums.AlbumApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivitiesBuildersModule::class,
        PicassoModule::class,
        DataModule::class,
        ContextModule::class
    ]
)

    interface AppComponent : AndroidInjector<AlbumApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: AlbumApplication)
}