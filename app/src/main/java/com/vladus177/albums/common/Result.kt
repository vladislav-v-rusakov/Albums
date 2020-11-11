package com.vladus177.albums.common

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T>(val throwable: Throwable) : Result<T>()
    class Loading<T> : Result<T>()
    class Cancel<T> : Result<T>()
    class Empty<T> : Result<T>()
}
