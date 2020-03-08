package com.vladus177.albums.common.extension

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Call<T>.await() = suspendCoroutine<T?> { continuation ->
    enqueue(object : Callback<T?> {
        override fun onResponse(call: Call<T?>, response: Response<T?>) {
            if (response.isSuccessful)
                continuation.resume(response.body())
            else
                continuation.resumeWithException(HttpException(response))
        }

        override fun onFailure(call: Call<T?>, t: Throwable) {
            continuation.resumeWithException(t)
        }
    })
}