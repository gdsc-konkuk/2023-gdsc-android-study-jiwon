package com.example.week1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week1.ToDoData

class NameViewModel: ViewModel() {
    private val _currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    //    이거 대신에 fun getToDoList(): LiveData<List<ToDoData>>로 만드는 것은 어떨까?
    val currentName: LiveData<String>
        get() = _currentName

    fun setName(newName: String) {
        _currentName.value = newName
    }
}