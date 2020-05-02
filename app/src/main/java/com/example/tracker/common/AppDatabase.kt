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

@Database(
    entities = [Exercise::class, WSet::class, User::class, Workout::class, Entry::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exercisesDao(): ExercisesDao
    abstract fun entriesDao(): EntriesDao
    abstract fun workoutsDao(): WorkoutsDao
    abstract fun setsDao(): SetsDao
    abstract fun usersDao(): UsersDao

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
                            database.exercisesDao(),
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

        suspend fun populateDatabase(
            exercisesDao: ExercisesDao, entriesDao: EntriesDao,
            workoutsDao: WorkoutsDao, setsDao: SetsDao, usersDao: UsersDao
        ) {
            println(" *** POPULATING DATABASE ***")
            exercisesDao.deleteAll()
            setsDao.deleteAll()
            usersDao.deleteAll()
            workoutsDao.deleteAll()
            entriesDao.deleteAll()

            val benchPress = exercisesDao.insert(Exercise("bench press"))
            val cableCurl = exercisesDao.insert(Exercise("cable curl"))
            val legPress = exercisesDao.insert(Exercise("leg press"))
            val legCurl = exercisesDao.insert(Exercise("leg curl"))
            println(" *** 00000")
            val jc = usersDao.insert(User("jctorres"))
            println(" *** 000000aaaa")
            println(jc)
            TrackerApp().userId = jc
            println(" *** 000000cccc")
            val workoutjc1 = workoutsDao.insert(Workout("W1", jc))

            println(" *** 11111")

            val entryjc4 = entriesDao.insert(Entry(workoutjc1, benchPress))
            setsDao.insert(WSet(5.5, 8, entryjc4))
            setsDao.insert(WSet( 5.5, 8, entryjc4))

            println(" *** 22222")

            val entryjc5 = entriesDao.insert(Entry(workoutjc1, cableCurl))
            setsDao.insert(WSet(6.5, 8, entryjc5))
            setsDao.insert(WSet( 6.5, 8, entryjc5))

            val workoutjc2 = workoutsDao.insert(Workout("W2", jc))
            val entryjc1 = entriesDao.insert(Entry(workoutjc2, legPress))
            setsDao.insert(WSet(7.5, 8, entryjc1))
            setsDao.insert(WSet(7.5, 8, entryjc1))

            val entryjc3 = entriesDao.insert(Entry(workoutjc2, legCurl))
            setsDao.insert(WSet( 8.5, 8, entryjc3))
            setsDao.insert(WSet(8.5, 8, entryjc3))
        }
    }
}