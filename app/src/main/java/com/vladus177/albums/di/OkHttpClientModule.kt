package com.vladus177.albums.di

import android.content.Context
import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.File


@Module
class OkHttpClientModule {

    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

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
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }
        })
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

}