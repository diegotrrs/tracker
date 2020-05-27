package com.example.tracker.workout

import com.example.tracker.common.daos.SetsDao
import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao
import com.example.tracker.common.entities.Exercise
import com.example.tracker.common.entities.WSet

class SetsRepository private constructor(private val setsDao: SetsDao) {

    suspend fun createSet(entryId: Long, weight: Double, reps: Short) = setsDao.insert(WSet(weight, reps, entryId))

    suspend fun updateSet(set: WSet) = setsDao.update(set)

    companion object {
        @Volatile
        private var instance: SetsRepository? = null

        fun getInstance(setsDao: SetsDao) = instance ?: synchronized(this) {
            instance ?: SetsRepository(setsDao).also { instance = it }
        }
    }
}