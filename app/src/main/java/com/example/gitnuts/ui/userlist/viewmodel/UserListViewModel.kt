package com.example.gitnuts.ui.userlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gitnuts.data.repository.UserRepository
import com.example.gitnuts.utils.UIState
import com.example.gitnuts.ui.base.BaseViewModel
import com.example.gitnuts.ui.userlist.model.UserItemMapper
import com.example.gitnuts.ui.userlist.model.UserItemUIModel
import com.example.gitnuts.utils.ResultAPI
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository,
    private val mapper: UserItemMapper
) : BaseViewModel() {

    private val _userListResponse: MutableLiveData<UIState<List<UserItemUIModel>>> = MutableLiveData()
    val userListResponse: LiveData<UIState<List<UserItemUIModel>>> = _userListResponse


    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _userListResponse.value = UIState.Loading
            val result = repository.getUsers()
            _userListResponse.value = when(result) {
                is ResultAPI.OnSuccess -> UIState.Success(result.data.map { mapper.map(it) })
                is ResultAPI.OnError -> UIState.Error(result.exception)
            }
        }
    }
}