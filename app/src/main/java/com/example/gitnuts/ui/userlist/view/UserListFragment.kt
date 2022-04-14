package com.example.gitnuts.ui.userlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitnuts.R
import com.example.gitnuts.data.repository.RepositoryProvider
import com.example.gitnuts.databinding.UserListFragmentBinding
import com.example.gitnuts.utils.UIState
import com.example.gitnuts.ui.base.BaseFragment
import com.example.gitnuts.ui.userdetail.view.UserDetailFragment
import com.example.gitnuts.ui.userlist.model.UserItemMapper
import com.example.gitnuts.ui.userlist.viewmodel.UserListViewModel
import com.example.gitnuts.ui.userlist.viewmodel.UserListViewModelFactory
import com.example.gitnuts.ui.userlist.model.UserItemUIModel

class UserListFragment : BaseFragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserListViewModel by viewModels {
        UserListViewModelFactory(
            RepositoryProvider().provideUserRepository(),
            UserItemMapper()
        )
    }
    private lateinit var binding: UserListFragmentBinding
    private lateinit var adapter: UserListRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserListFragmentBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initUI() {
        adapter = UserListRvAdapter(listOf(), object : UserListRvAdapter.ClickEvents {
            override fun onContainerClicked(v: View, item: UserItemUIModel) {
                onItemClicked(item)
            }

        })
        binding.recView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@UserListFragment.adapter
        }
    }

    private fun initObservers() {
        viewModel.userListResponse.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> { /*TODO show loading progress*/ }
                is UIState.Success -> onGetUserListSuccess(it.data)
                is UIState.Error ->   showError(it.error)
            }
        }
    }

    fun onItemClicked(data: UserItemUIModel) {
        //Nav to detail fragment

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, UserDetailFragment.newInstance().apply {
            arguments = Bundle().apply { putString(UserDetailFragment.USER_DATA, data.username) } })
            .addToBackStack(null)
            .commit()
    }

    fun onGetUserListSuccess(list: List<UserItemUIModel>) {
        adapter.updateData(list)
    }





}