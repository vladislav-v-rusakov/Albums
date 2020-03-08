package com.vladus177.albums.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.extension.setError
import com.vladus177.albums.common.extension.setLoading
import com.vladus177.albums.common.extension.setSuccess
import com.vladus177.albums.domain.GetImageListUseCase
import com.vladus177.albums.ui.mapper.ImageViewMapper
import com.vladus177.albums.ui.model.ImageView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val imageViewMapper: ImageViewMapper
) : ViewModel() {

    val images = MutableLiveData<Resource<List<ImageView>>>()

    private val compositeDisposable = CompositeDisposable()

    fun loadImageList(albumId: Long, forceUpdate: Boolean) {
        compositeDisposable.add(getImageListUseCase.getImageList(albumId, forceUpdate)
            .doOnSubscribe { images.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.map { with(imageViewMapper) { it.fromDomainToView() } } }
            .subscribe({ images.setSuccess(it) }, { images.setError(it.message) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}