package com.ayaabdelaziz.roomdatabasedemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.ayaabdelaziz.roomdatabasedemo.data.UserDatabase
import com.ayaabdelaziz.roomdatabasedemo.model.User
import com.ayaabdelaziz.roomdatabasedemo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val readData: LiveData<List<User>>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readData = repository.readUser
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)

        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)

        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            repository.deleteAllUser()
        }
    }

    fun searchInList(query: String): LiveData<List<User>> {
        return repository.searchInList(query).asLiveData()
    }


}