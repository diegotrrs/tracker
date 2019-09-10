package com.example.tracker.ui.common

import androidx.lifecycle.LiveData
import com.example.tracker.ui.home.Exercise
import com.example.tracker.ui.home.ExerciseDao

class TrackerRepository(private val exercisesDao: ExerciseDao){
    val allExercises : LiveData<List<Exercise>> = exercisesDao.getAll();

    suspend fun insertExercise(exercise: Exercise){
        exercisesDao.insert(exercise)
    }
}