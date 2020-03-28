package com.example.tracker.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tracker.common.daos.*
import com.example.tracker.common.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Exercise::class, WSet::class, User::class, Workout::class, Entry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun unitsDao(): UnitDao
    abstract fun entriesDao(): EntriesDao
    abstract fun workoutsDao(): WorkoutDao
    abstract fun setsDao(): SetDao
    abstract fun usersDao(): UserDao

    companion object {
        @Volatile
        private var appDatabase: AppDatabase? = null;

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return appDatabase ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tracker_database"
                ).fallbackToDestructiveMigration().addCallback(
                    Callback(
                        scope
                    )
                ).build()
                appDatabase = instance
                instance
            }
        }

        private class Callback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the appDatabase.
             * For this sample, we clear the appDatabase every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                appDatabase?.let { database ->
                    scope.launch {
                        populateDatabase(
                            database.unitsDao(),
                            database.entriesDao(),
                            database.workoutsDao(),
                            database.setsDao(),
                            database.usersDao()
                        )
                    }
                }

            }
        }

        /**
         * Populate the appDatabase in a new coroutine.
         * If you want to start with more words, just add them.
         */

        suspend fun populateDatabase(unitDao: UnitDao, entriesDao: EntriesDao,
                                     workoutDao: WorkoutDao, setsDao: SetDao, userDao: UserDao) {

            println(" *** POPULATING DATABASE ***")
            unitDao.deleteAll()
            setsDao.deleteAll()
            userDao.deleteAll()
            workoutDao.deleteAll()
            entriesDao.deleteAll()


            val benchPress = unitDao.insert(Exercise("bench press"))
            val cableCurl = unitDao.insert(Exercise("cable curl"))
            val legPress = unitDao.insert(Exercise("leg press"))
            val legCurl = unitDao.insert(Exercise("leg curl"))

            val jc = userDao.insert( User("jctorres"))
            val workoutjc1 = workoutDao.insert(Workout( 3000, jc))
            val entryjc4 = entriesDao.insert(Entry(workoutjc1, benchPress))
            setsDao.insert(WSet(5.5, 8,  entryjc4))
            setsDao.insert(WSet(5.5, 8,  entryjc4))

            val entryjc5 = entriesDao.insert(Entry(workoutjc1, cableCurl))
            setsDao.insert(WSet(6.5, 8,  entryjc5))
            setsDao.insert(WSet(6.5, 8,  entryjc5))

            val workoutjc2 = workoutDao.insert(Workout( 4000, jc))
            val entryjc1 = entriesDao.insert(Entry(workoutjc2, legPress))
            setsDao.insert(WSet(7.5, 8,  entryjc1))
            setsDao.insert(WSet(7.5, 8,  entryjc1))

            val entryjc3 = entriesDao.insert(Entry(workoutjc2, legCurl))
            setsDao.insert(WSet(8.5, 8,  entryjc3))
            setsDao.insert(WSet(8.5, 8,  entryjc3))


        }


    }

}