package com.example.gitnuts.ui.userdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitnuts.data.model.User
import com.example.gitnuts.data.repository.UserRepository
import com.example.gitnuts.ui.userdetail.model.UserDetailMapper

class UserDetailViewModelFactory(
    val repository: UserRepository,
    val selectedUserName: String, val mapper: UserDetailMapper = UserDetailMapper()
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java, String::class.java, UserDetailMapper::class.java).newInstance(repository, selectedUserName, mapper)
    }
}