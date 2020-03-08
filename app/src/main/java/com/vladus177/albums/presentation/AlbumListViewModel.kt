package com.vladus177.albums.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.extension.setError
import com.vladus177.albums.common.extension.setLoading
import com.vladus177.albums.common.extension.setSuccess
import com.vladus177.albums.domain.GetAlbumListUseCase
import com.vladus177.albums.ui.mapper.AlbumViewMapper
import com.vladus177.albums.ui.model.AlbumView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    private val getAlbumListUseCase: GetAlbumListUseCase,
    private val albumMapper: AlbumViewMapper
) : ViewModel() {

    val albums = MutableLiveData<Resource<List<AlbumView>>>()

    private val compositeDisposable = CompositeDisposable()

    fun loadAlbumList(userId: Long, forceUpdate: Boolean) {
        compositeDisposable.add(getAlbumListUseCase.getAlbumList(userId, forceUpdate)
            .doOnSubscribe { albums.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.map { with(albumMapper) { it.fromDomainToView() } } }
            .subscribe({ albums.setSuccess(it) }, { albums.setError(it.message) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}