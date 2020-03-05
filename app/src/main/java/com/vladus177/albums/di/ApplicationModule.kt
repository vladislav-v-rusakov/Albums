package vladus177.com.albums.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import vladus177.com.albums.data.AlbumRepositoryImpl
import vladus177.com.albums.data.AlbumsDataSource
import vladus177.com.albums.data.AlbumsRepository
import vladus177.com.albums.data.local.AlbumsLocalDataSource
import vladus177.com.albums.data.local.UsersDatabase
import vladus177.com.albums.data.remote.AlbumsRemoteDataSource
import vladus177.com.albums.data.remote.AlbumsRestApi
import vladus177.com.albums.data.remote.AlbumsRestApiFactory
import javax.inject.Qualifier
import javax.inject.Singleton

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
        restApi: AlbumsRestApi,
        ioDispatcher: CoroutineDispatcher
    ): AlbumsDataSource {
        return AlbumsRemoteDataSource(restApi, ioDispatcher)
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