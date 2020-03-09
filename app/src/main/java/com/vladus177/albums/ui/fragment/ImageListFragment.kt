package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.ResourceState
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.databinding.FragmentImageListBinding
import com.vladus177.albums.presentation.ImageListViewModel
import com.vladus177.albums.ui.adapter.ImageListAdapter
import com.vladus177.albums.ui.mapper.ImageViewMapper
import com.vladus177.albums.ui.model.ImageView
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentImageListBinding.inflate(inflater, container, false)
        dynamicInfo = viewDataBinding.diImageInfo
        dynamicInfo.setActionButtonClickListener(clickListener = { loadImageList(networkStateManager.isConnectedOrConnecting) })
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        viewModel.images.observe(viewLifecycleOwner, Observer { updateImages(it) })
        loadImageList(true)
    }

    private fun loadImageList(forceUpdate: Boolean) {
        viewModel.loadImageList(args.albumId, forceUpdate)
    }

    private fun updateImages(resource: Resource<List<ImageView>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> dynamicInfo.showLoading()
                ResourceState.SUCCESS -> {
                    dynamicInfo.hideLoading()
                    it.data?.let {
                        if (it.isEmpty() && !networkStateManager.isConnectedOrConnecting) {
                            dynamicInfo.showConnectionError()
                        }
                    }
                }
                ResourceState.ERROR -> dynamicInfo.showError()
            }
            it.data?.let { listAdapter.submitList(it) }
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