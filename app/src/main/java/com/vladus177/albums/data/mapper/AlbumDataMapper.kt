package com.vladus177.albums.data.mapper

import com.vladus177.albums.data.local.model.AlbumEntity
import com.vladus177.albums.data.remote.model.AlbumEntry
import com.vladus177.albums.domain.model.AlbumModel
import javax.inject.Inject

class AlbumDataMapper @Inject constructor() {

    fun AlbumEntry.fromDataToDomain() = AlbumModel(
        userId = userId,
        id = id,
        title = title
    )

    fun AlbumEntity.fromEntityToDomain() = AlbumModel(
        id = id,
        userId = userId,
        title = albumTitle
    )

    fun AlbumModel.fromDomainToEntity() = AlbumEntity(
        id = id,
        userId = userId,
        albumTitle = title
    )
}