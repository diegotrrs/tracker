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

@Database(entities = [Exercise::class], version = 2)
abstract class TrackerDatabase : RoomDatabase() {

    abstract fun exercisesDao(): ExerciseDao

    companion object {
        @Volatile
        private var trackerDatabase: com.example.tracker.home.TrackerDatabase? = null;

        fun getDatabase(context: Context, scope: CoroutineScope): com.example.tracker.home.TrackerDatabase {
            return trackerDatabase ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackerDatabase::class.java,
                    "tracker_database"
                ).fallbackToDestructiveMigration().addCallback(
                    Callback(
                        scope
                    )
                ).build()
                trackerDatabase = instance
                instance
            }
        }

        private class Callback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the trackerDatabase.
             * For this sample, we clear the trackerDatabase every time it is created or opened.
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
         * Populate the trackerDatabase in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(exerciseDao: ExerciseDao) {
            // Start the app with a clean trackerDatabase every time.
            // Not needed if you only populate on creation.
            exerciseDao.deleteAll()

            var dates = HashMap<String, Long>()

            dates["Wed 18 Sep 2019 19:12:47 GMT"] = 1568833967000;
            dates["Tue 17 Sep 2019 19:12:47 GMT"] = 1568747567000;
            dates["Sat 14 Sep 2019 19:12:47 GMT"] = 1568488367000;
            dates["Fri 13 Sep 2019 19:12:47 GMT"] = 1568401967000;
            dates["Tue 10 Sep 2019 19:12:47 GMT"] = 1568142767000;
            dates["Mon 2 Sep 2019 19:12:47 GMT"] = 1567451567000;
            dates["Tue 27 Aug 2019 19:12:47 GMT"] = 1566933167000;
            dates["Mon 26 Aug 2019 19:12:47 GMT"] = 1566846767000;
            dates["Sat 17 Aug 2019 19:12:47 GMT"] = 1566069167000;

            for((k,v) in dates){
                System.out.println("k ${k} v ${v} ")
                exerciseDao.insert(Exercise(k, v))
            }
            /*for(i in 1..15){
                exerciseDao.insert(Exercise("Ex $i"))
            }*/

        }
    }

}