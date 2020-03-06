package com.vladus177.albums.domain.model

data class ImagesByAlbumIdParam(
    val forceUpdate: Boolean,
    val albumId: Long
)