package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.Exercise
import com.example.tracker.common.entities.User
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

@Dao
interface ExercisesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise): Long

    @Query("SELECT * FROM exercises")
    fun getAll(): LiveData<List<Exercise>>

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()
}
