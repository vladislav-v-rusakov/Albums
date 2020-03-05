package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.R
import com.vladus177.albums.common.Result
import com.vladus177.albums.common.extension.observe
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.databinding.FragmentAlbumListBinding
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.presentation.AlbumListViewModel
import com.vladus177.albums.ui.adapter.AlbumListAdapter
import com.vladus177.albums.ui.adapter.OnAlbumItemClickListener
import com.vladus177.albums.ui.mapper.AlbumViewMapper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AlbumListFragment : DaggerFragment(), OnAlbumItemClickListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var networkStateManager: NetworkStateManager
    @Inject lateinit var mapper: AlbumViewMapper

    private val viewModel by viewModels<AlbumListViewModel> { viewModelFactory }
    private lateinit var dynamicInfo: DynamicInformation
    private lateinit var viewDataBinding: FragmentAlbumListBinding
    private val args: AlbumListFragmentArgs by navArgs()
    private lateinit var listAdapter: AlbumListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupObserver()
        if (networkStateManager.isConnectedOrConnecting) {
            loadAlbumsList(true)
        } else {
            dynamicInfo.showConnectionError()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentAlbumListBinding.inflate(inflater, container, false)
        dynamicInfo = viewDataBinding.diAlbumInfo
        dynamicInfo.setActionButtonClickListener(clickListener = { loadAlbumsList(true) } )
        return viewDataBinding.root
    }

    private fun loadAlbumsList(forceUpdate: Boolean) {
        viewModel.getAlbumList(args.userId, forceUpdate)
    }

    private fun setupObserver() {
        with(viewModel) {
            observe(albumLiveData, ::albumDataObserver)
        }
    }

    private fun albumDataObserver(result: Result<List<AlbumModel>>?) {
        when (result) {
            is Result.OnLoading -> {
                dynamicInfo.showLoading()
            }
            is Result.OnSuccess -> {
                dynamicInfo.hideLoading()
                val albumList = with(mapper) {
                    result.value.map {
                        it.fromDomainToView()
                    }
                }
                listAdapter.submitList(albumList)
            }
            is Result.OnEmpty -> {
                dynamicInfo.showEmptyList()
            }
            is Result.OnError -> {
                dynamicInfo.showError()
            }
            else -> {
                dynamicInfo.hideLoading()
            }
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