package com.example.tracker.ui.home

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()

    @Query("SELECT * FROM exercises")
    fun getAll(): LiveData<List<Exercise>>


}

