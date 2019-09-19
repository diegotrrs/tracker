package com.example.tracker.home

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tracker.home.workouts.Exercise
import kotlinx.coroutines.CoroutineScope
import com.example.tracker.home.workouts.ExerciseDao
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun exercisesDao(): ExerciseDao

    companion object {
        @Volatile
        private var database: com.example.tracker.home.Database? = null;

        fun getDatabase(context: Context, scope: CoroutineScope): com.example.tracker.home.Database {
            return database ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "tracker_database"
                ).fallbackToDestructiveMigration().addCallback(
                    Callback(
                        scope
                    )
                ).build()
                database = instance
                instance
            }
        }

        private class Callback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                database?.let { database ->
                    scope.launch {
                        populateDatabase(database.exercisesDao())
                    }
                }

            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(exerciseDao: ExerciseDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            exerciseDao.deleteAll()

            for(i in 1..15){
                exerciseDao.insert(Exercise("Ex $i"))
            }

        }
    }

}