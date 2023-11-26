package com.example.week1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    /*@Query("SELECT * FROM users " +
            "WHERE name LIKE :name " +
            "LIMIT 1")
    fun findByName(name: String): LiveData<User>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun delete(user: User)

    /*@Query("SELECT * from users WHERE uid = :id")
    fun getItem(id: Int): Flow<User>

    @Query("SELECT * from users ORDER BY name ASC")
    fun getItems(): Flow<List<User>>*/
}