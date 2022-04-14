package com.example.gitnuts.ui.userdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gitnuts.data.repository.UserRepository
import com.example.gitnuts.utils.UIState
import com.example.gitnuts.ui.base.BaseViewModel
import com.example.gitnuts.ui.userdetail.model.UserDetailMapper
import com.example.gitnuts.ui.userdetail.model.UserDetailUIModel
import com.example.gitnuts.utils.ResultAPI
import kotlinx.coroutines.launch

class UserDetailViewModel(private val repository: UserRepository, private val selectedUserName: String, private val mapper: UserDetailMapper) : BaseViewModel() {

    private val _userDetailResponse: MutableLiveData<UIState<UserDetailUIModel>> = MutableLiveData()
    val userDetailResponse: LiveData<UIState<UserDetailUIModel>> = _userDetailResponse

    init {
        getUserDetail()
    }


    fun getUserDetail() {
        _userDetailResponse.value = UIState.Loading
        viewModelScope.launch {
            val result = repository.getUserByUsername(selectedUserName)
            _userDetailResponse.value = when(result) {
                is ResultAPI.OnSuccess -> UIState.Success(mapper.map(result.data))
                is ResultAPI.OnError -> UIState.Error(result.exception)
            }
        }
    }
}