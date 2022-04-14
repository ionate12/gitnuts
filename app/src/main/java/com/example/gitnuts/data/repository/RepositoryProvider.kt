package com.example.gitnuts.data.repository

import com.example.gitnuts.data.api.APIService
import com.example.gitnuts.data.api.RetrofitClient

class RepositoryProvider {
    private val retrofit = RetrofitClient.getRetrofit()
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(retrofit.create(
        APIService::class.java))
}