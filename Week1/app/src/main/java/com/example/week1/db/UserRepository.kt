package com.example.week1.db

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val users: LiveData<List<User>>
        get() = userDao.getAll()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}
