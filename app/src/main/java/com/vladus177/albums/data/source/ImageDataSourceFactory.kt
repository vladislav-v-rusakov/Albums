package com.vladus177.albums.data.source

import com.vladus177.albums.data.repository.ImageLocal
import com.vladus177.albums.data.repository.ImageRemote
import javax.inject.Inject

class ImageDataSourceFactory @Inject constructor(
    private val imageRemote: ImageRemote,
    private val imageLocal: ImageLocal
) {

    fun getRemote(): ImageRemote = imageRemote

    fun getLocal(): ImageLocal = imageLocal
}