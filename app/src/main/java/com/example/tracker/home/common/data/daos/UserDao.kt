package com.example.tracker.home.common.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.home.common.data.entities.XUser
import com.example.tracker.home.common.data.entities.XUserAndWorkouts
import com.example.tracker.home.common.data.entities.XUserAndWorkoutsAndExercises
import com.example.tracker.home.common.data.entities.XWorkoutAndExercises

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XUser: XUser): Long

    @Query("SELECT * FROM users WHERE username =:username")
    fun getUserWorkouts(username: String): LiveData<List<XUserAndWorkouts>>

    @Query("SELECT * FROM users WHERE username =:username")
    fun getUser(username: String): LiveData<List<XUserAndWorkoutsAndExercises>>

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<XUser>>
}
