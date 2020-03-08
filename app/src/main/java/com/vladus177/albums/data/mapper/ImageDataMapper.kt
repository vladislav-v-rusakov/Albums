package com.vladus177.albums.data.mapper

import com.vladus177.albums.data.local.model.ImageEntity
import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.domain.model.ImageModel
import javax.inject.Inject

class ImageDataMapper @Inject constructor() {

    fun ImageEntry.fromDataToDomain() = ImageModel(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnail = thumbnail,
        uriPath = ""
    )

    fun ImageModel.fromDomainToEntity() = ImageEntity(
        albumId = albumId,
        id = id,
        imageTitle = title,
        url = url,
        thumbnailUrl = thumbnail,
        uriString = ""
    )

    fun ImageEntity.fromEntityToDomain() = ImageModel(
        albumId = albumId,
        id = id,
        title = imageTitle,
        url = url,
        thumbnail = thumbnailUrl,
        uriPath = ""
    )
}