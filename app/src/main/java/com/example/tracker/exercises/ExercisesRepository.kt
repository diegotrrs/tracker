package com.example.tracker.exercises

import com.example.tracker.common.daos.ExercisesDao
import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao
import com.example.tracker.common.entities.Exercise
import com.example.tracker.workouts.WorkoutsRepository

class ExercisesRepository private constructor(private val exercisesDao: ExercisesDao) {

    suspend fun createExercise(exerciseName: String) = exercisesDao.insert(Exercise(exerciseName))
    suspend fun deleteExercise(exerciseId: Long) = exercisesDao.deleteExercise(exerciseId)
    fun getExercises() = exercisesDao.getAll()

    companion object {
        @Volatile
        private var instance: ExercisesRepository? = null

        fun getInstance(exercisesDao: ExercisesDao) = instance ?: synchronized(this) {
            instance ?: ExercisesRepository(exercisesDao).also { instance = it }
        }
    }
}