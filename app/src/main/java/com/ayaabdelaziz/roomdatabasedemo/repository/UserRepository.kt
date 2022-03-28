package com.ayaabdelaziz.roomdatabasedemo.repository

import androidx.lifecycle.LiveData
import com.ayaabdelaziz.roomdatabasedemo.data.UserDao
import com.ayaabdelaziz.roomdatabasedemo.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readUser : LiveData<List<User>> = userDao.readUser()

    suspend fun insertUser(user:User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

    fun searchInList(query:String): Flow<List<User>> {
        return userDao.searchInList(query)
    }



}