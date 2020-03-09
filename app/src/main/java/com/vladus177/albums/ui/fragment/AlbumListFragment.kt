package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.ResourceState
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.databinding.FragmentAlbumListBinding
import com.vladus177.albums.presentation.AlbumListViewModel
import com.vladus177.albums.ui.adapter.AlbumListAdapter
import com.vladus177.albums.ui.adapter.OnAlbumItemClickListener
import com.vladus177.albums.ui.model.AlbumView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AlbumListFragment : DaggerFragment(), OnAlbumItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkStateManager: NetworkStateManager


    private val viewModel by viewModels<AlbumListViewModel> { viewModelFactory }
    private lateinit var dynamicInfo: DynamicInformation
    private lateinit var viewDataBinding: FragmentAlbumListBinding
    private val args: AlbumListFragmentArgs by navArgs()
    private lateinit var listAdapter: AlbumListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentAlbumListBinding.inflate(inflater, container, false)
        dynamicInfo = viewDataBinding.diAlbumInfo
        dynamicInfo.setActionButtonClickListener(clickListener = {
            loadAlbumsList(
                networkStateManager.isConnectedOrConnecting
            )
        })
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        viewModel.albums.observe(viewLifecycleOwner, Observer { updateAlbums(it) })
        loadAlbumsList(networkStateManager.isConnectedOrConnecting)
    }

    private fun loadAlbumsList(forceUpdate: Boolean) {
        viewModel.loadAlbumList(args.userId, forceUpdate)
    }

    private fun updateAlbums(resource: Resource<List<AlbumView>>?) {
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

    override fun onItemClicked(albumId: Long?) {
        let {
            val action =
                AlbumListFragmentDirections.actionAlbumListFragmentToImageListFragment(albumId!!)
            findNavController().navigate(action)
        }
    }

    private fun setupListAdapter() {
        listAdapter = AlbumListAdapter(this)
        viewDataBinding.albumList.adapter = listAdapter
        viewDataBinding.albumList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}