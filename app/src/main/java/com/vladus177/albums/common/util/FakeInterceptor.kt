package com.vladus177.albums.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class FakeInterceptor constructor(private val context: Context) : Interceptor {

    private var mContentType = "application/json"

    @SuppressLint("DefaultLocale")
    override fun intercept(chain: Interceptor.Chain): Response {
        SystemClock.sleep(LOAD_TIME)
        val method = chain.request().method.toLowerCase()
        val defaultFileName: String? = getFileName(chain)
        var response: Response? = null
        val uri = chain.request().url.toUri()
        val mockDataPath = uri.host + uri.path + "/".toLowerCase()
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString())

        try {
            Log.d(TAG, "Read data from file: $mockDataPath + $defaultFileName")
            val jsonFile: String =
                context.assets.open(mockDataPath + defaultFileName).bufferedReader()
                    .use { it.readText() }
            lateinit var jsonObject: Any
            if (jsonFile.trim().first() == '[') {
                jsonObject = JSONArray(jsonFile)
            } else if (jsonFile.trim().first() == '{') {
                jsonObject = JSONObject(jsonFile)
            }

            Log.d(TAG, "Response: $jsonObject")

            val builder = Response.Builder()
            builder.request(chain.request())
            builder.protocol(Protocol.HTTP_1_0)
            builder.addHeader("content-type", mContentType)
            builder.body(
                jsonObject.toString().toByteArray().toResponseBody(mContentType.toMediaTypeOrNull())
            )
            builder.code(200)
            builder.message(jsonObject.toString())
            response = builder.build()
        } catch (e: IOException) {
            Log.e(TAG, e.message, e)
        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString())
        return response!!
    }

    @SuppressLint("DefaultLocale")
    private fun getFileName(chain: Interceptor.Chain): String {
        val fileName =
            chain.request().url.pathSegments[chain.request().url.pathSegments.size - 1]
        return if (fileName.isEmpty()) "index$FILE_EXTENSION" else fileName + "_" + chain.request().method.toLowerCase() + FILE_EXTENSION
    }

    companion object {
        private val TAG = FakeInterceptor::class.java.simpleName
        private const val FILE_EXTENSION = ".json"
        private const val LOAD_TIME = 1500.toLong()
    }

}