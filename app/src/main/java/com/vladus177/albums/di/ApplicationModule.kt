package com.vladus177.albums.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.vladus177.albums.data.AlbumRepositoryImpl
import com.vladus177.albums.data.AlbumsDataSource
import com.vladus177.albums.data.AlbumsRepository
import com.vladus177.albums.data.local.AlbumsLocalDataSource
import com.vladus177.albums.data.local.UsersDatabase
import com.vladus177.albums.data.remote.AlbumsRemoteDataSource
import com.vladus177.albums.data.remote.AlbumsRestApi
import com.vladus177.albums.data.remote.AlbumsRestApiFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import android.net.ConnectivityManager
import com.vladus177.albums.common.util.NetworkStateManager


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
        database: UsersDatabase,
        ioDispatcher: CoroutineDispatcher
    ): AlbumsDataSource {
        return AlbumsLocalDataSource(
            database.usersDao(), ioDispatcher
        )
    }

    /*@JvmStatic
    @Singleton
    @Provides
    fun provideAlbumsRestApi(): AlbumsRestApi {
        return AlbumsRestApiFactory().albumsApi
    }*/

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "Users.db"
        ).build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideNetworkStateManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
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