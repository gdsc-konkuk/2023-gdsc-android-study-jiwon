package com.example.week1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week1.ToDoData

class ToDoViewModel : ViewModel() {
    private val _currentToDoList: MutableLiveData<List<ToDoData>> by lazy {
        MutableLiveData<List<ToDoData>>().also { it.value = loadData() }
    }

//    이거 대신에 fun getToDoList(): LiveData<List<ToDoData>>로 만드는 것이 더 좋지 않을까?
    val currentToDoList: LiveData<List<ToDoData>>
        get() = _currentToDoList

    fun setToDoList(todoList: List<ToDoData>) {
        _currentToDoList.value = todoList
    }

    fun getDoneCount(): Int {
        requireNotNull(_currentToDoList.value) { "ToDoViewModel's _currentToDoList is null" }

        var count = 0
        for (todo in _currentToDoList.value!!) {
            if(todo.done) count++
        }

        return count
    }

    fun getCurrentToDoList(): List<ToDoData> {
        return currentToDoList.value!!
    }

    fun addNewTodo(todo: ToDoData) {
        val newList = mutableListOf<ToDoData>()
        newList.addAll(requireNotNull(_currentToDoList.value))
        newList.add(todo)
        _currentToDoList.value = newList
    }

    private fun loadData(): List<ToDoData> {
        return listOf(
            ToDoData(false, "안녕하세요", "0"),
            ToDoData(false, "안녕하세요1", "1"),
            ToDoData(false, "안녕하세요2", "2"),
            ToDoData(false, "안녕하세요3", "3"),
            ToDoData(true, "안녕하세요4", "0"),
            ToDoData(true, "안녕하세요5", "0"),
        )
    }

}