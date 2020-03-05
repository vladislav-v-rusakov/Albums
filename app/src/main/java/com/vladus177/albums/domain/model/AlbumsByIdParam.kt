package com.vladus177.albums.domain.model

data class AlbumsByIdParam (
    val forceUpdate: Boolean,
    val userId: Long
)