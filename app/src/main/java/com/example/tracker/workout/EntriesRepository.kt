package com.example.tracker.workout

import com.example.tracker.common.daos.EntriesDao
import com.example.tracker.common.daos.SetsDao
import com.example.tracker.common.daos.UsersDao
import com.example.tracker.common.daos.WorkoutsDao
import com.example.tracker.common.entities.Entry
import com.example.tracker.common.entities.Exercise
import com.example.tracker.common.entities.WSet

class EntriesRepository private constructor(private val entriesDao: EntriesDao) {

    suspend fun createEntry(workoutId: Long, exerciseId: Long): Long = entriesDao.insert(Entry(workoutId, exerciseId))
    suspend fun deleteEntry(entryId: Long) = entriesDao.deleteEntry(entryId)

    companion object {
        @Volatile
        private var instance: EntriesRepository? = null

        fun getInstance(entriesDao: EntriesDao) = instance ?: synchronized(this) {
            instance ?: EntriesRepository(entriesDao).also { instance = it }
        }

    }
}