package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.common.Result
import com.vladus177.albums.common.extension.observe
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.databinding.FragmentAlbumListBinding
import com.vladus177.albums.databinding.FragmentImageListBinding
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.presentation.AlbumListViewModel
import com.vladus177.albums.presentation.ImageListViewModel
import com.vladus177.albums.ui.adapter.AlbumListAdapter
import com.vladus177.albums.ui.adapter.ImageListAdapter
import com.vladus177.albums.ui.mapper.AlbumViewMapper
import com.vladus177.albums.ui.mapper.ImageViewMapper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ImageListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkStateManager: NetworkStateManager
    @Inject
    lateinit var mapper: ImageViewMapper
    @Inject
    lateinit var listAdapter: ImageListAdapter

    private val viewModel by viewModels<ImageListViewModel> { viewModelFactory }
    private lateinit var dynamicInfo: DynamicInformation
    private lateinit var viewDataBinding: FragmentImageListBinding
    private val args: ImageListFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupObserver()
        loadImageList(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentImageListBinding.inflate(inflater, container, false)
        dynamicInfo = viewDataBinding.diImageInfo
        dynamicInfo.setActionButtonClickListener(clickListener = { loadImageList(true) })
        return viewDataBinding.root
    }

    private fun loadImageList(forceUpdate: Boolean) {
        viewModel.getImagesListByAlbumId(args.albumId, forceUpdate)
    }

    private fun setupObserver() {
        with(viewModel) {
            observe(imageLiveData, ::imageDataObserver)
        }
    }

    private fun imageDataObserver(result: Result<List<ImageModel>>?) {
        when (result) {
            is Result.OnLoading -> {
                dynamicInfo.showLoading()
            }
            is Result.OnSuccess -> {
                dynamicInfo.hideLoading()
                val imageList = with(mapper) {
                    result.value.map {
                        it.fromDomainToView()
                    }
                }
                listAdapter.submitList(imageList)
            }
            is Result.OnEmpty -> {
                if (networkStateManager.isConnectedOrConnecting) {
                    dynamicInfo.showEmptyList()
                } else {
                    dynamicInfo.showConnectionError()
                }
            }
            is Result.OnError -> {
                dynamicInfo.showError()
            }
            else -> {
                dynamicInfo.hideLoading()
            }
        }
    }

    private fun setupListAdapter() {
        viewDataBinding.imageList.adapter = listAdapter
        viewDataBinding.imageList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}