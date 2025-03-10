package com.BasicAndroid.basicandroid.DI

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyRepository @Inject constructor(val myDataSource: MyDataSource) {

   fun getDataSourceUsers(): Flow<Users> = myDataSource.getData()

    suspend fun getUpdateSourceUsers(users: Users){
        myDataSource.addData(users)
    }
}