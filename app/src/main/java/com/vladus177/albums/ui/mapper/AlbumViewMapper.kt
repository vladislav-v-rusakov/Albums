package com.vladus177.albums.ui.mapper

import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.ui.model.AlbumView
import javax.inject.Inject

class AlbumViewMapper @Inject constructor() {

    fun AlbumModel.fromDomainToView() = AlbumView(
        userId = userId,
        id = id,
        title = title
    )

}