package com.example.gitnuts.data.repository

import com.example.gitnuts.utils.NetworkStatus
import com.example.gitnuts.utils.ResultAPI
import java.lang.Exception

open class BaseRepository {
    fun getNetworkStatus(): NetworkStatus {
        //TODO check network connectivity here!!
        return NetworkStatus.Wifi
    }

    suspend fun <T: Any> handleRequest(requestFunc: suspend () -> T): ResultAPI<T> {
        return try {
            ResultAPI.OnSuccess(requestFunc.invoke())
        } catch (e: Exception) {
            ResultAPI.OnError(e)
        }
    }
}