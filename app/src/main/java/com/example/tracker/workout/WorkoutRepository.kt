package com.example.tracker.workout

import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao

class WorkoutRepository private constructor(private val workoutsDao: WorkoutsDao) {
    fun getWorkoutById(workoutId: Long) = workoutsDao.getWorkoutById(workoutId)

    companion object {
        @Volatile
        private var instance: WorkoutRepository? = null

        fun getInstance(workoutsDao: WorkoutsDao) = instance ?: synchronized(this) {
            instance ?: WorkoutRepository(workoutsDao).also { instance = it }
        }

    }
}