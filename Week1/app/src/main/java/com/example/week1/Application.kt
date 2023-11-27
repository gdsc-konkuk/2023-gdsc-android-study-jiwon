package com.example.week1

import android.app.Application
import com.example.week1.db.Todo
import com.example.week1.db.User
import com.example.week1.db.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Application: Application() {

    val database: UserRoomDatabase by lazy { UserRoomDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
//        CoroutineScope(Dispatchers.IO).launch {
//            database.userDao().insertUser(User(name = "박지원", imageUrl = "https://images.unsplash.com/photo-1698050556933-240364f29622?ixid=M3w0MTIxNzd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MDEwMTg4Mjh8&ixlib=rb-4.0.3"))
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            database.todoDao().insertAllTodo(*arrayOf(Todo(isDone = false, content = "해야할일1"), Todo(isDone = false, content = "해야할일2"),
//                Todo(isDone = false, content = "해야할일3"), Todo(isDone = false, content = "해야할일4"),
//                Todo(isDone = true, content = "해야할일5"), Todo(isDone = true, content = "해야할일6")))
//        }
    }
}