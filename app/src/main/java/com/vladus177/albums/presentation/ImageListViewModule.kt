package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.domain.GetImageListUseCase
import javax.inject.Inject

class ImageListViewModule @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModel() {
}