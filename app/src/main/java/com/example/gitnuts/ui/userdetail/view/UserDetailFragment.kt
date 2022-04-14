package com.example.gitnuts.ui.userdetail.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitnuts.R
import com.example.gitnuts.data.repository.RepositoryProvider
import com.example.gitnuts.databinding.UserDetailFragmentBinding
import com.example.gitnuts.utils.UIState
import com.example.gitnuts.ui.base.BaseFragment
import com.example.gitnuts.ui.userdetail.viewmodel.UserDetailViewModel
import com.example.gitnuts.ui.userdetail.viewmodel.UserDetailViewModelFactory

class UserDetailFragment : BaseFragment() {

    companion object {
        const val USER_DATA = "USER_DATA"
        fun newInstance() = UserDetailFragment()
    }

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var binding: UserDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserDetailFragmentBinding.inflate(inflater, container, false )
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username: String? = arguments?.getString(USER_DATA)
        if (username == null) {
            showError(Throwable(getString(R.string.user_detail_no_data)))
            return
        }
        viewModel = ViewModelProvider(this, UserDetailViewModelFactory(
            RepositoryProvider().provideUserRepository(),
            username
        )
        )[UserDetailViewModel::class.java]
        initObservers()
    }

    private fun initUI() {
        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initObservers() {
        viewModel.userDetailResponse.observe(viewLifecycleOwner) {
            when(it) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    //Bind to view
                    binding.userDetail = it.data
                }
                is UIState.Error -> showError(it.error)
            }
        }
    }


}