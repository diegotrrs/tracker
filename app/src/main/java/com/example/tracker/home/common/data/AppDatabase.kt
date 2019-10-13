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
                                     workoutDao: WorkoutDao, setsDao: SetDao, userDao: UserDao       ) {
            // Start the app with a clean appDatabase every time.
            // Not needed if you only populate on creation.
            unitDao.deleteAll()
            userDao.deleteAll()
            workoutDao.deleteAll()
            setsDao.deleteAll()
            exerciseDao.deleteAll()


            // Workout -> Exercise -> Set


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

            println("***** DATABASE SETUP ****")
           // Users
            val userId = userDao.insert( XUser("jeanc"))
            println("***** USER ID ${userId} ****")

            var workout1 = workoutDao.insert(XWorkout( 3000, userId))
            println("***** WORKOUT 1 ID ${workout1} ****")
            var workout2 = workoutDao.insert(XWorkout( 4000, userId))
            println("***** WORKOUT 2 ID ${workout2} ****")

            var exercise1 = exerciseDao.insert(XExercise(workout1))
            var exercise2 = exerciseDao.insert(XExercise(workout2))

            var set11 = setsDao.insert(XSet(5, 8, exercise1))
            println("***** SET 11 ID ${set11} ****")
            var set12 = setsDao.insert(XSet(5, 8, exercise1))
            println("***** SET 12 ID ${set12} ****")

            var set21 = setsDao.insert(XSet(6, 8, exercise2))
            println("***** SET 21 ID ${set21} ****")
            var set22 = setsDao.insert(XSet(7, 8, exercise2))
            println("***** SET 22 ID ${set22} ****")
        }
    }

}