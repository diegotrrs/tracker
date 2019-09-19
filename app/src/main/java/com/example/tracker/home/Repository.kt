package com.example.tracker.home

import androidx.lifecycle.LiveData
import com.example.tracker.home.workouts.Exercise
import com.example.tracker.home.workouts.ExerciseDao

class Repository(private val exercisesDao: ExerciseDao){
    val allExercises : LiveData<List<Exercise>> = exercisesDao.getAll();

    suspend fun insertExercise(exercise: Exercise){
        exercisesDao.insert(exercise)
    }
}