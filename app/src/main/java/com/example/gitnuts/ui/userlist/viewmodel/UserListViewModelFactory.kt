package com.example.gitnuts.ui.userlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitnuts.data.repository.UserRepository
import com.example.gitnuts.ui.userlist.model.UserItemMapper

class UserListViewModelFactory(
    val repository: UserRepository,
    val mapper: UserItemMapper,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java, UserItemMapper::class.java).newInstance(repository, mapper)
    }
}