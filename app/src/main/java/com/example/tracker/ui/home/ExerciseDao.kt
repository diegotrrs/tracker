package com.example.tracker.ui.home

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


interface ExerciseDao {
    @Insert
    fun insert(exercise: Exercise)

    @Query("DELETE FROM EXERCISES")
    fun deleteAllExercises()

    @Query("SELECT * FROM EXERCISES")
    fun getAllExercises(exercise: Exercise): LiveData<Exercise>
}