package com.example.gitnuts.data.api

import com.example.gitnuts.BuildConfig
import com.example.gitnuts.utils.getGson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BASE_URL = BuildConfig.BASE_URL

    fun getRetrofit(): Retrofit = Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                                    .build()
}