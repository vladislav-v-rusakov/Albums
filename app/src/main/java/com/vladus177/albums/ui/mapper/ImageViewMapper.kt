package com.vladus177.albums.ui.mapper

import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.ui.model.ImageView
import javax.inject.Inject

class ImageViewMapper @Inject constructor()  {

    fun ImageModel.fromDomainToView() = ImageView(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnail = thumbnail,
        uriPath = uriPath
    )

}