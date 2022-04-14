package com.example.gitnuts.utils

sealed class ResultAPI<out T : Any?> {

    class OnSuccess<out T : Any?>(val data: T) : ResultAPI<T>()
    class OnError(val exception: Throwable) : ResultAPI<Nothing>()

}