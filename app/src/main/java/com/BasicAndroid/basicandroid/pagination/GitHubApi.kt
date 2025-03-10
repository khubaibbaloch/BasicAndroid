package com.BasicAndroid.basicandroid.pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int = 10,
    ): List<GitHubUser>

    companion object{
        const val BASE_URL = "https://api.github.com/"
    }
}