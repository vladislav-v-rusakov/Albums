package com.vladus177.albums.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides

import com.vladus177.albums.data.remote.AlbumsRestApiFactory
import javax.inject.Singleton
import android.net.ConnectivityManager
import com.squareup.picasso.Picasso
import com.vladus177.albums.common.PostExecutionThread
import com.vladus177.albums.common.UiThread
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.data.AlbumDataRepository
import com.vladus177.albums.data.ImageDataRepository
import com.vladus177.albums.data.UserDataRepository
import com.vladus177.albums.data.local.*
import com.vladus177.albums.data.remote.AlbumRemoteRepositoryImpl
import com.vladus177.albums.data.remote.ImageRemoteRepositoryImpl
import com.vladus177.albums.data.remote.UserRemoteRepositoryImpl
import com.vladus177.albums.data.repository.*
import com.vladus177.albums.domain.AlbumRepository
import com.vladus177.albums.domain.ImageRepository
import com.vladus177.albums.domain.UserRepository
import com.vladus177.albums.ui.adapter.ImageListAdapter


@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @JvmStatic
    @Provides
    fun provideUserDataBase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "Users.db"
        ).build()
    }

    @JvmStatic
    @Provides
    fun provideUserDao(db: UserDatabase) = db.usersDao()

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
    @Singleton
    @Provides
    fun provideAlbumDao(db: AlbumDatabase) = db.albumsDao()

    @JvmStatic
    @Singleton
    @Provides
    fun provideImageDataBase(context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ImageDatabase::class.java,
            "Image.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideImageDao(db: ImageDatabase) = db.imageDao()


    @JvmStatic
    @Provides
    fun provideNetworkStateManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    @JvmStatic
    @Provides
    fun provideImageListAdapter(picasso: Picasso): ImageListAdapter {
        return ImageListAdapter(picasso)
    }

    @JvmStatic
    @Provides
    fun provideAlbumsRestApi() = AlbumsRestApiFactory().albumsApi
}

@Module
abstract class ApplicationModuleBinds {


    @Binds
    abstract fun bindUserDataRepository(dataRepository: UserDataRepository): UserRepository

    @Binds
    abstract fun bindUserLocal(userLocal: UserLocalRepositoryImpl): UserLocal

    @Binds
    abstract fun bindUserRemote(userRemote: UserRemoteRepositoryImpl): UserRemote


    @Binds
    abstract fun bindAlbumDataRepository(dataRepository: AlbumDataRepository): AlbumRepository

    @Binds
    abstract fun bindAlbumLocal(albumLocal: AlbumLocalRepositoryImpl): AlbumLocal

    @Binds
    abstract fun bindAlbumRemote(albumRemote: AlbumRemoteRepositoryImpl): AlbumRemote


    @Binds
    abstract fun bindImageDataRepository(dataRepository: ImageDataRepository): ImageRepository

    @Binds
    abstract fun bindImageLocal(imageLocal: ImageLocalRepositoryImpl): ImageLocal

    @Binds
    abstract fun bindImageRemote(imageRemote: ImageRemoteRepositoryImpl): ImageRemote


    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}