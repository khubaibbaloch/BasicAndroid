package com.BasicAndroid.basicandroid.RetrofitPractice

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .header("x-api-key", apiKey) // Add your specific header here
            .build()
        return chain.proceed(requestWithApiKey)
    }
}