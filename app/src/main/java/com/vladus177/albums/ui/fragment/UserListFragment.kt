package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladus177.albums.databinding.FragmentUsersListBinding
import com.vladus177.albums.presentation.UserListViewModel
import com.vladus177.albums.ui.adapter.UsersListAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.ResourceState
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.ui.adapter.OnItemClickListener
import com.vladus177.albums.ui.model.UserView


open class UserListFragment : DaggerFragment(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkStateManager: NetworkStateManager

    private val viewModel by viewModels<UserListViewModel> { viewModelFactory }
    private lateinit var viewDataBinding: FragmentUsersListBinding
    private lateinit var listAdapter: UsersListAdapter
    private lateinit var dynamicInfo: DynamicInformation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentUsersListBinding.inflate(inflater, container, false)
        dynamicInfo = viewDataBinding.diUserInfo
        dynamicInfo.setActionButtonClickListener(clickListener = { loadUserList(networkStateManager.isConnectedOrConnecting) })
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        viewModel.users.observe(viewLifecycleOwner, Observer { updateUsers(it) })
        loadUserList(networkStateManager.isConnectedOrConnecting)
    }

    private fun loadUserList(forceUpdate: Boolean) {
        viewModel.loadUserList(forceUpdate)
    }

    private fun updateUsers(resource: Resource<List<UserView>>?) {
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

    override fun onItemClicked(userId: Long?) {
        let {
            val action =
                UserListFragmentDirections.actionUserListFragmentToAlbumsListFragment(userId!!)
            findNavController().navigate(action)
        }
    }

    override fun onFavoriteClicked(userId: Long, favorite: Boolean) {
        viewModel.setFavorite(userId, favorite)
    }

    private fun setupListAdapter() {
        listAdapter = UsersListAdapter(this)
        viewDataBinding.userList.adapter = listAdapter
        viewDataBinding.userList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}