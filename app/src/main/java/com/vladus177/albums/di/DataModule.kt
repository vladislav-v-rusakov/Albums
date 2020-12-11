package com.vladus177.albums.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.vladus177.albums.BuildConfig
import com.vladus177.albums.common.PostExecutionThread
import com.vladus177.albums.common.UiThread
import com.vladus177.albums.common.util.FakeInterceptor
import com.vladus177.albums.data.repository.AlbumsDataRepository
import com.vladus177.albums.data.local.*
import com.vladus177.albums.data.remote.AlbumRemoteSourceRepositoryImpl
import com.vladus177.albums.data.remote.AlbumsRestApi
import com.vladus177.albums.data.remote.converter.UserDeserializer
import com.vladus177.albums.data.remote.model.UserEntry
import com.vladus177.albums.data.repository.*
import com.vladus177.albums.domain.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module (includes = [DataModuleBinds::class])
class DataModule {

    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10 MB
    }

    @Provides
    fun provideFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }


    @Provides
    fun provideAlbumsRestApi(context: Context, cache: Cache): AlbumsRestApi {
        val okHttpBuilder = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .cache(cache)

        if (BuildConfig.FLAVOR.equals("dummy")) {
            okHttpBuilder.addInterceptor(FakeInterceptor(context))
        }

        val userDeserializer =
            GsonBuilder().registerTypeAdapter(UserEntry::class.java, UserDeserializer()).create()

        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(BuildConfig.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(userDeserializer))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AlbumsRestApi::class.java)
    }

    @Provides
    fun provideUserDataBase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "Users.db"
        ).build()
    }


    @Provides
    fun provideUserDao(db: UserDatabase) = db.usersDao()

    @Singleton
    @Provides
    fun provideAlbumsDataBase(context: Context): AlbumDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AlbumDatabase::class.java,
            "Album.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(db: AlbumDatabase) = db.albumsDao()

    @Singleton
    @Provides
    fun provideImageDataBase(context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ImageDatabase::class.java,
            "Image.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideImageDao(db: ImageDatabase) = db.imageDao()

}

@Module
abstract class DataModuleBinds {

    @Binds
    abstract fun bindAlbumDataRepository(dataRepository: AlbumsDataRepository): AlbumRepository

    @Binds
    abstract fun bindAlbumLocal(albumLocal: AlbumsLocalSourceRepositoryImpl): AlbumsLocalSource

    @Binds
    abstract fun bindAlbumRemote(albumRemote: AlbumRemoteSourceRepositoryImpl): AlbumRemoteSource

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}