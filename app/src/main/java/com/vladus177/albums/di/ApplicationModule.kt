package com.vladus177.albums.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import com.vladus177.albums.data.AlbumRepositoryImpl
import com.vladus177.albums.data.AlbumsDataSource
import com.vladus177.albums.data.AlbumsRepository
import com.vladus177.albums.data.local.UserDatabase
import com.vladus177.albums.data.remote.AlbumsRemoteDataSource
import com.vladus177.albums.data.remote.AlbumsRestApi
import com.vladus177.albums.data.remote.AlbumsRestApiFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import android.net.ConnectivityManager
import com.squareup.picasso.Picasso
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.data.local.AlbumDatabase
import com.vladus177.albums.data.local.AlbumsLocalDataSource
import com.vladus177.albums.ui.adapter.ImageListAdapter


@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AlbumsRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AlbumsLocalDataSource

    @JvmStatic
    @Singleton
    @AlbumsRemoteDataSource
    @Provides
    fun provideAlbumsRemoteDataSource(
        restApi: AlbumsRestApi
    ): AlbumsDataSource {
        return AlbumsRemoteDataSource(restApi)
    }

    @JvmStatic
    @Singleton
    @AlbumsLocalDataSource
    @Provides
    fun provideAlbumsLocalDataSource(
        userDatabase: UserDatabase,
        albumDatabase: AlbumDatabase
    ): AlbumsDataSource {
        return AlbumsLocalDataSource(
            userDatabase.usersDao(),
            albumDatabase.albumsDao()
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideUserDataBase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "Users.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAlbumsDataBase(context: Context): AlbumDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AlbumDatabase::class.java,
            "Album.db"
        ).build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkStateManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    @JvmStatic
    @Provides
    fun provideImageListAdapter(picasso: Picasso): ImageListAdapter {
        return ImageListAdapter(picasso)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @JvmStatic
    @Singleton
    @Provides
    fun provideAlbumsRestApi() = AlbumsRestApiFactory().albumsApi
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: AlbumRepositoryImpl): AlbumsRepository
}