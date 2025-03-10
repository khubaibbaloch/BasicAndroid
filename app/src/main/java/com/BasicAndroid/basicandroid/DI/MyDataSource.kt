package com.BasicAndroid.basicandroid.DI

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyDataSource @Inject constructor() {
    private val userList = mutableListOf<Users>()
    fun getData(): Flow<Users> {
        return flow {
            repeat(10) { index ->
                //delay(1000)
                // Emit each user one by one
                val user = Users(id = index, name = "Khubaib", age = 21)
                userList.add(user)
                emit(user)
            }

        }.flowOn(Dispatchers.IO)
            .buffer(1)
    }
    suspend fun addData(users: Users){
        val user = Users(id =  users.id, name = users.name, age = users.age)
        userList.add(user)
    }

}