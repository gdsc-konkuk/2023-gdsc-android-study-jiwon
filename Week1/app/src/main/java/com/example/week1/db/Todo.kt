package com.example.week1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo("is_done")
    val isDone: Boolean,
    @ColumnInfo("content")
    val content: String,
    @ColumnInfo("created_at")
    val createdAt: String = "0"
)