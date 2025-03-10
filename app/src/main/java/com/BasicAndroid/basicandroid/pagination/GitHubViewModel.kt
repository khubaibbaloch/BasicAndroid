package com.BasicAndroid.basicandroid.pagination

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlin.text.get

class GitHubViewModel : ViewModel() {
    private val repository = GitHubRepository()

    val users: Flow<PagingData<GitHubUser>> = repository.getUsers().cachedIn(viewModelScope)

}