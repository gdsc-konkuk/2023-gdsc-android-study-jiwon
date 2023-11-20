package com.example.week1.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users " +
            "WHERE name LIKE :name " +
            "LIMIT 1")
    fun findByName(name: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    /*@Query("SELECT * from users WHERE uid = :id")
    fun getItem(id: Int): Flow<User>

    @Query("SELECT * from users ORDER BY name ASC")
    fun getItems(): Flow<List<User>>*/
}