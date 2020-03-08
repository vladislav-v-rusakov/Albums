package com.vladus177.albums.data.source

import com.vladus177.albums.data.repository.AlbumLocal
import com.vladus177.albums.data.repository.AlbumRemote
import javax.inject.Inject

class AlbumDataSourceFactory @Inject constructor(
    private val albumRemote: AlbumRemote,
    private val albumLocal: AlbumLocal
) {

    fun getRemote(): AlbumRemote = albumRemote

    fun getLocal(): AlbumLocal = albumLocal
}