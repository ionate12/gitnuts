package com.example.gitnuts.data.repository

import com.example.gitnuts.data.api.APIService
import com.example.gitnuts.data.api.RetrofitClient
import com.example.gitnuts.data.model.User
import com.example.gitnuts.data.model.UserDetail
import com.example.gitnuts.utils.ResultAPI


interface UserRepository {
    suspend fun getUsers(): ResultAPI<List<User>>
    suspend fun getUserByUsername(userName: String): ResultAPI<UserDetail>
}
class UserRepositoryImpl(
    private val apiService: APIService = RetrofitClient.getRetrofit().create(APIService::class.java)
// TODO add dbService to support offlineMode
): BaseRepository(), UserRepository {
    override suspend fun getUsers(): ResultAPI<List<User>> {
        //TODO: In order to support offline mode. we will need a check to know whether the device is offline/online, then perform getData from different datasource.
        return handleRequest { apiService.getUsers() }
    }

    override suspend fun getUserByUsername(userName: String): ResultAPI<UserDetail> {
        return handleRequest { apiService.getUserByUsername(userName) }
    }

}