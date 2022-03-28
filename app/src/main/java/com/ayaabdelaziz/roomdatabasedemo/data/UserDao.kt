package com.ayaabdelaziz.roomdatabasedemo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ayaabdelaziz.roomdatabasedemo.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_data")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun readUser(): LiveData<List<User>>

    @Query("SELECT * FROM user_data WHERE firstName LIKE :query OR secondName LIKE :query")
    fun searchInList(query:String):Flow<List<User>>


}