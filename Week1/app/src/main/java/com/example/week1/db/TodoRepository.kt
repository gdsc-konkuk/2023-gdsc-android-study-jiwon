package com.example.week1.db

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val todoList: LiveData<List<Todo>>
        get() = todoDao.getAll()

    suspend fun insert(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun update(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}