package com.BasicAndroid.basicandroid.RetrofitPractice

import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
            .header("x-api-key",apiKey)
            .header("Content-Type", "application/json")
            .build()
        return chain.proceed(requestWithHeaders)
    }

}