package com.example.tracker.workouts

import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao

class WorkoutsRepository private constructor(private val workoutsDao: WorkoutsDao, private val usersDao: UsersDao) {
    suspend fun getWorkouts(username: String) = usersDao.getUser(username)

    companion object {
        @Volatile
        private var instance: WorkoutsRepository? = null

        fun getInstance(workoutsDao: WorkoutsDao, usersDao: UsersDao) = instance ?: synchronized(this) {
            instance ?: WorkoutsRepository(workoutsDao, usersDao).also { instance = it }
        }

    }
}