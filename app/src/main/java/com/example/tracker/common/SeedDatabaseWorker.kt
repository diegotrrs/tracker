package com.example.tracker.common

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tracker.common.entities.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import com.example.tracker.common.daos.*
import com.example.tracker.common.entities.*

class SeedDatabaseWorker(
    context: Context, workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = AppDatabase.getInstance(applicationContext)
            val exercisesDao = database.exercisesDao()
            val setsDao = database.setsDao()
            val usersDao = database.usersDao()
            val workoutsDao = database.workoutsDao()
            val entriesDao = database.entriesDao()

            val benchPress = exercisesDao.insert(Exercise("bench press"))
            val cableCurl = exercisesDao.insert(Exercise("cable curl"))
            val legPress = exercisesDao.insert(Exercise("leg press"))
            val legCurl = exercisesDao.insert(Exercise("leg curl"))

            val jc = usersDao.insert(User("jctorres"))
            TrackerApp.userId = jc

            val workoutjc1 = workoutsDao.insert(Workout("W1", jc))
            val entryjc4 = entriesDao.insert(Entry(workoutjc1, benchPress))
            setsDao.insert(WSet(5.5, 8, entryjc4))
            setsDao.insert(WSet(5.5, 8, entryjc4))

            val entryjc5 = entriesDao.insert(Entry(workoutjc1, cableCurl))
            setsDao.insert(WSet(6.5, 8, entryjc5))
            setsDao.insert(WSet(6.5, 8, entryjc5))

            val workoutjc2 = workoutsDao.insert(Workout("W2", jc))
            val entryjc1 = entriesDao.insert(Entry(workoutjc2, legPress))
            setsDao.insert(WSet(7.5, 8, entryjc1))
            setsDao.insert(WSet(7.5, 8, entryjc1))

            val entryjc3 = entriesDao.insert(Entry(workoutjc2, legCurl))
            setsDao.insert(WSet(8.5, 8, entryjc3))
            setsDao.insert(WSet(8.5, 8, entryjc3))
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }
}