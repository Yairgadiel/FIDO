package com.gy.whatsnew.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    class NetworkError<out T>(val data: Throwable? = null) : Resource<T>()
    class Loading<out T> : Resource<T>()
}