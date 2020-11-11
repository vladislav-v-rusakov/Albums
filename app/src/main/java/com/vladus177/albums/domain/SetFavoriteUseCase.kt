package com.vladus177.albums.domain

import io.reactivex.Completable
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(private val repository: AlbumRepository) {
    fun setFavorite(userId: Long, favorite: Boolean): Completable =
        repository.setFavoriteUser(userId, favorite)
}