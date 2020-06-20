package com.vladus177.albums.di

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import okhttp3.OkHttpClient
import dagger.Provides
import com.squareup.picasso.Picasso
import dagger.Module
import java.util.concurrent.TimeUnit

@Module
class PicassoModule {

    @Provides
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }

    @Provides
    fun provideOkHttp3Downloader(): OkHttp3Downloader {
        val okHttpBuilder = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        return OkHttp3Downloader(okHttpBuilder.build())
    }

}