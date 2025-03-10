package com.BasicAndroid.basicandroid.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class GitHubPagingSource(
    private val api: GitHubApi,
) : PagingSource<Int, GitHubUser>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubUser> {
        val position = params.key ?: 0
        return try {
            val response = api.getUsers(since = position, perPage = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 10,
                nextKey = if (response.isEmpty()) null else position + 10
            )

        }catch (e: IOException) {
            LoadResult.Error(e)
        }catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitHubUser>): Int? {
        return state.anchorPosition
    }
}