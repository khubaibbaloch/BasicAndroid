package com.BasicAndroid.basicandroid.pagination

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubRepository {

    private val api: GitHubApi = Retrofit.Builder()
        .baseUrl(GitHubApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    fun getUsers(): Flow<PagingData<GitHubUser>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { GitHubPagingSource(api) }
        ).flow
    }
}