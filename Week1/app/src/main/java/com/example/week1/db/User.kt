package com.example.week1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("image_url")
    val imageUrl: String
)
