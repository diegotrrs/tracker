package com.example.tracker.common

import androidx.lifecycle.LiveData
import com.example.tracker.common.daos.ExercisesDao
import com.example.tracker.common.daos.UserDao
import com.example.tracker.common.entities.Exercise
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

class AppRepository(private val userDao: UserDao, private val exercisesDao: ExercisesDao){
    val workouts : LiveData<List<UserAndWorkoutsAndEntries>> = userDao.getUser("jctorres");
    val exercises : LiveData<List<Exercise>> = exercisesDao.getAll();

    suspend fun createExercise(exerciseName: String){
        exercisesDao.insert( Exercise(exerciseName))
    }

    suspend fun deleteExercise(exerciseId: Long){
        exercisesDao.deleteExercise(exerciseId)
    }
}