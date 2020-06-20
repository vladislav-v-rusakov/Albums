package com.vladus177.albums.di

import android.content.Context
import android.net.ConnectivityManager
import com.squareup.picasso.Picasso

import com.vladus177.albums.common.util.NetworkStateManager

import com.vladus177.albums.ui.adapter.ImageListAdapter
import dagger.Module
import dagger.Provides


@Module
object ApplicationModule {

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

}

