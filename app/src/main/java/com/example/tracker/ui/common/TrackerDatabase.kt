package com.example.tracker.ui.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tracker.ui.home.Exercise
import kotlinx.coroutines.CoroutineScope
import com.example.tracker.ui.home.ExerciseDao
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class], version = 1)
abstract class TrackerDatabase : RoomDatabase() {

    abstract fun exercisesDao(): ExerciseDao

    companion object {
        @Volatile
        private var trackerDatabase: TrackerDatabase? = null;

        fun getDatabase(context: Context, scope: CoroutineScope): TrackerDatabase {
            return trackerDatabase ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackerDatabase::class.java,
                    "tracker_database"
                ).fallbackToDestructiveMigration().addCallback(Callback(scope)).build()
                trackerDatabase = instance
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
                trackerDatabase?.let { database ->
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

            var exercise = Exercise("Ex1")
            exerciseDao.insert(exercise)
            exercise = Exercise("Ex2")
            exerciseDao.insert(exercise)
        }
    }

}