package com.alexisdev.common

sealed class Response<T> {
    data class Loading<T>(val data: T? = null): Response<T>()
    data class Success<T>(val data: T? = null): Response<T>()
    data class Error<T>(val msg: String? = null): Response<T>()
}