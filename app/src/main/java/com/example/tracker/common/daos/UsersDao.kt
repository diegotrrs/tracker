package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.User
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(User: User): Long

    @Transaction
    @Query("SELECT * FROM users WHERE username =:username")
    fun getUser(username: String): LiveData<List<UserAndWorkoutsAndEntries>>

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>
}
