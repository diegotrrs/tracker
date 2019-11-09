package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.XUser
import com.example.tracker.common.entities.XUserAndWorkoutsAndExercises

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XUser: XUser): Long

    @Transaction
    @Query("SELECT * FROM users WHERE username =:username")
    fun getUser(username: String): LiveData<List<XUserAndWorkoutsAndExercises>>

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<XUser>>
}
