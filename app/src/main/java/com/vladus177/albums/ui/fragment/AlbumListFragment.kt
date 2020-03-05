package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.vladus177.albums.R
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.databinding.FragmentAlbumListBinding
import com.vladus177.albums.presentation.AlbumListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AlbumListFragment : DaggerFragment() {

    private lateinit var viewDataBinding: FragmentAlbumListBinding

    private val args: AlbumListFragmentArgs by navArgs()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var networkStateManager: NetworkStateManager

    private val viewModel by viewModels<AlbumListViewModel> { viewModelFactory }
    private lateinit var dynamicInfo: DynamicInformation

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

    private fun setupObserver() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupListAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album_list, container, false)
        dynamicInfo = viewDataBinding.info
        dynamicInfo.setActionButtonClickListener(clickListener = { loadAlbumsList(true) } )
        return view
    }

    private fun loadAlbumsList(forceUpdate: Boolean) {
        viewModel.getAlbumList(args.userId, forceUpdate)
    }
}