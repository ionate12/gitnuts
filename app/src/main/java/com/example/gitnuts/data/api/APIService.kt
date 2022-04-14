package com.example.gitnuts.data.api

import com.example.gitnuts.data.model.User
import com.example.gitnuts.data.model.UserDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/users/{userName}")
    suspend fun getUserByUsername(@Path("userName") userName: String): UserDetail
}