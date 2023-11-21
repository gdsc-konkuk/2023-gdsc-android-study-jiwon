package com.example.week1

import android.app.Application
import com.example.week1.db.UserRoomDatabase

class Application: Application() {

    val database: UserRoomDatabase by lazy { UserRoomDatabase.getDatabase(this) }

}