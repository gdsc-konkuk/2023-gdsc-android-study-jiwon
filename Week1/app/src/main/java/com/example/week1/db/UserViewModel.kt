package com.example.week1.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDao: UserDao
): ViewModel() {

    private val repository: UserRepository = UserRepository(userDao)
    var currentUser: LiveData<List<User>> = repository.users

    private fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun addNewUser(name: String, url: String) {
        val newItem = getNewUserEntry(name, url)
        insertUser(newItem)
        currentUser = repository.users
    }

    fun getAllUser(): LiveData<List<User>> = currentUser

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.update(user)
        }
        currentUser = repository.users
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.delete(user)
        }
        currentUser = repository.users
    }
    private fun getNewUserEntry(name: String, url: String): User {
        return User(
            name = name,
            imageUrl = url
        )
    }


    fun isEntryValid(name: String, imageUrl: String): Boolean {
        if (name.isBlank() || imageUrl.isBlank()) {
            return false
        }
        return true
    }

}

class UserViewModelFactory(
    private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

