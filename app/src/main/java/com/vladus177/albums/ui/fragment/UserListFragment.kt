package com.vladus177.albums.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladus177.albums.common.extension.observe
import com.vladus177.albums.databinding.FragmentUsersListBinding
import com.vladus177.albums.presentation.UserListViewModel
import com.vladus177.albums.ui.adapter.UsersListAdapter
import com.vladus177.albums.common.Result
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.ui.mapper.UserViewMapper
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
import com.vladus177.albums.common.view.DynamicInformation
import com.vladus177.albums.common.util.NetworkStateManager
import com.vladus177.albums.ui.adapter.OnItemClickListener


class UserListFragment : DaggerFragment(), OnItemClickListener {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var usersListMapper: UserViewMapper
    @Inject lateinit var networkStateManager: NetworkStateManager

    private val viewModel by viewModels<UserListViewModel> { viewModelFactory }
    private lateinit var viewDataBinding: FragmentUsersListBinding
    private lateinit var listAdapter: UsersListAdapter
    private lateinit var dynamicInfo: DynamicInformation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentUsersListBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        dynamicInfo = viewDataBinding.diUserInfo
        dynamicInfo.setActionButtonClickListener(clickListener = { loadUserList(true) })
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupObserver()
        if (networkStateManager.isConnectedOrConnecting) {
            loadUserList(true)
        } else {
            dynamicInfo.showConnectionError()
        }
    }

    private fun loadUserList(forceUpdate: Boolean) {
        viewModel.loadUserList(forceUpdate)
    }

    private fun setupObserver() {
        with(viewModel) {
            observe(userLiveData, ::userDataObserver)
        }
    }

    private fun userDataObserver(result: Result<List<UserModel>>?) {
        when (result) {
            is Result.OnLoading -> {
                dynamicInfo.showLoading()
            }
            is Result.OnSuccess -> {
                dynamicInfo.hideLoading()
                val userList = with(usersListMapper) {
                    result.value.map {
                        it.fromDomainToView()
                    }
                }
                listAdapter.submitList(userList)
            }
            is Result.OnEmpty -> {
            }
            is Result.OnError -> {
                dynamicInfo.hideLoading()
            }
            else -> {
                dynamicInfo.hideLoading()
            }
        }
    }

    override fun onItemClicked(userId: Long?) {
        let {
            val action =
                UserListFragmentDirections.actionUserListFragmentToAlbumsListFragment(userId!!)
            findNavController().navigate(action)
        }
    }

    override fun onFavoriteClicked(userId: Long?, favorite: Boolean) {
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