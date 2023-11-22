package com.example.week1.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val userDao: UserDao
): ViewModel() {

    private fun insertUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    private fun getNewUserEntry(name: String, url: String): User {
        return User(
            name = name,
            imageUrl = url
        )
    }

    fun addNewUser(name: String, url: String) {
        val newItem = getNewUserEntry(name, url)
        insertUser(newItem)
    }

    fun isEntryValid(name: String, imageUrl: String): Boolean {
        if (name.isBlank() || imageUrl.isBlank()) {
            return false
        }
        return true
    }

    fun getAllUser(): Flow<List<User>> = userDao.getAll()

    fun updateUser(user: User) {
        viewModelScope.launch {
            userDao.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.delete(user)
        }
    }
}

class InventoryViewModelFactory(
    private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

