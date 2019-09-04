package com.example.tracker.ui.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tracker.ui.home.Exercise
import kotlinx.coroutines.CoroutineScope
import com.example.tracker.ui.home.ExerciseDao

@Database(entities = [Exercise::class], version = 1)
abstract class TrackerDatabase : RoomDatabase() {
    abstract fun exercisesDao(): ExerciseDao

    private var instance: TrackerDatabase? = null;

    fun getInstance(context: Context): TrackerDatabase? {
        if(instance == null){
            synchronized(TrackerDatabase::class){
                instance = Room.databaseBuilder(context.applicationContext, TrackerDatabase::class.java, "tracker_database").fallbackToDestructiveMigration().addCallback()
            }
        }
    }

    private class Callback(private val scope: CoroutineScope)

}