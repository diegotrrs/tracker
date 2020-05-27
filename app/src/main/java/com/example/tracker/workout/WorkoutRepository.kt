package com.example.tracker.workout

import com.example.tracker.common.TrackerApp
import com.example.tracker.common.daos.WorkoutsDao
import com.example.tracker.common.entities.Workout

class WorkoutRepository private constructor(private val workoutsDao: WorkoutsDao) {

    fun getWorkoutById(workoutId: Long) = workoutsDao.getWorkoutById(workoutId)

    suspend fun createWorkout(name: String): Long {
        var userId = TrackerApp.userId
        return workoutsDao.insert(Workout(name, userId))
    }

    companion object {
        @Volatile
        private var instance: WorkoutRepository? = null

        fun getInstance(workoutsDao: WorkoutsDao) = instance ?: synchronized(this) {
            instance ?: WorkoutRepository(workoutsDao).also { instance = it }
        }

    }
}