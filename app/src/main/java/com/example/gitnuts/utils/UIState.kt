package com.example.gitnuts.utils

sealed class UIState<out T : Any?> {
    class Success<out T : Any?>(val data: T): UIState<T>()
    object Loading : UIState<Nothing>()
    class Error(val error: Throwable): UIState<Nothing>()
}
