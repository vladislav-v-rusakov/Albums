package com.vladus177.albums.data.mapper

import com.vladus177.albums.data.remote.model.ImageEntry
import com.vladus177.albums.domain.model.ImageModel
import javax.inject.Inject

class ImageDataMapper @Inject constructor() {

    fun ImageEntry.fromDataToDomain() = ImageModel(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnail = thumbnail
    )
}