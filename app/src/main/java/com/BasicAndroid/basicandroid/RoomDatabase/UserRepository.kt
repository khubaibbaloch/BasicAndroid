package com.BasicAndroid.basicandroid.RoomDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.getAllUsers() // Flow for automatic updates

    suspend fun insertUser(user: User) = userDao.insertUser(user)
    //suspend fun getAllUsers(): List<User> = userDao.getALLUsers()
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()
}