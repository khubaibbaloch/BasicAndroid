package com.BasicAndroid.basicandroid.DI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val myRepository: MyRepository,
) : ViewModel() {
    private val _userData = MutableStateFlow<List<Users>>(emptyList())
    val userData: StateFlow<List<Users>> = _userData

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            myRepository.getDataSourceUsers().collect { users ->
//                _userData.value = _userData.value + users
//                _userData.value += users
                _userData.update { currentList ->
                    currentList + users
                }
            }
        }
    }

    fun updateData(users: Users){
        viewModelScope.launch{
            myRepository.getUpdateSourceUsers(users)
            _userData.value += users
        }
    }
}