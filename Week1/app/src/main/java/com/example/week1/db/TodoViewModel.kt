package com.example.week1.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(
    todoDao: TodoDao
) : ViewModel() {

    private val repository: TodoRepository = TodoRepository(todoDao)
    var currentTodoList: LiveData<List<Todo>> = repository.todoList

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repository.update(todo)
        }
        currentTodoList = repository.todoList
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
        currentTodoList = repository.todoList
    }

    fun isEntryValid(content: String): Boolean {
        if (content.isBlank()) {
            return false
        }
        return true
    }

}

class TodoViewModelFactory(
    private val todoDao: TodoDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(todoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}