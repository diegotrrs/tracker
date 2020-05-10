package com.example.tracker.workouts

import androidx.lifecycle.LiveData
import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

class WorkoutsRepository private constructor(private val workoutsDao: WorkoutsDao, private val usersDao: UsersDao) {
    fun getWorkouts(userId: Long): LiveData<List<UserAndWorkoutsAndEntries>> {
        println(" > WORKOUTS REPOSITORY ${userId} > ");
        return usersDao.getUser(userId);
    }

    companion object {
        @Volatile
        private var instance: WorkoutsRepository? = null

        fun getInstance(workoutsDao: WorkoutsDao, usersDao: UsersDao) = instance ?: synchronized(this) {
            instance ?: WorkoutsRepository(workoutsDao, usersDao).also { instance = it }
        }

    }
}