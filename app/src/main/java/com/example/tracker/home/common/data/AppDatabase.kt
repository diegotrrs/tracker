package com.example.tracker.home.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tracker.home.common.data.daos.*
import com.example.tracker.home.common.data.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [XUnit::class, XSet::class, XUser::class, XWorkout::class, XExercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun unitsDao(): UnitDao
    abstract fun exercisesDao(): ExerciseDao
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
                            database.exercisesDao(),
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

        suspend fun populateDatabase(unitDao: UnitDao, exerciseDao: ExerciseDao,
                                     workoutDao: WorkoutDao, setsDao: SetDao, userDao: UserDao) {

            println(" *** POPULATING DATABASE ***")
            unitDao.deleteAll()
            setsDao.deleteAll()
            userDao.deleteAll()
            workoutDao.deleteAll()
            exerciseDao.deleteAll()


            val benchPress = unitDao.insert(XUnit("bench press"))
            val cableCurl = unitDao.insert(XUnit("cable curl"))
            val legPress = unitDao.insert(XUnit("leg press"))
            val legCurl = unitDao.insert(XUnit("leg curl"))

            val jc = userDao.insert( XUser("jctorres"))
            val workoutjc1 = workoutDao.insert(XWorkout( 3000, jc))
            val exercisejc4 = exerciseDao.insert(XExercise(workoutjc1, "bench press"))
            setsDao.insert(XSet(5.5, 8,  exercisejc4))
            setsDao.insert(XSet(5.5, 8,  exercisejc4))

            val exercisejc5 = exerciseDao.insert(XExercise(workoutjc1, "cable curl"))
            setsDao.insert(XSet(6.5, 8,  exercisejc5))
            setsDao.insert(XSet(6.5, 8,  exercisejc5))

            val workoutjc2 = workoutDao.insert(XWorkout( 4000, jc))
            val exercisejc1 = exerciseDao.insert(XExercise(workoutjc2, "leg press"))
            setsDao.insert(XSet(7.5, 8,  exercisejc1))
            setsDao.insert(XSet(7.5, 8,  exercisejc1))

            val exercisejc2 = exerciseDao.insert(XExercise(workoutjc2, "leg curl"))
            setsDao.insert(XSet(8.5, 8,  exercisejc2))
            setsDao.insert(XSet(8.5, 8,  exercisejc2))


        }


    }

}