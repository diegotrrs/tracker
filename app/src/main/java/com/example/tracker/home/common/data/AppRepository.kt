package com.example.tracker.home.common.data

import androidx.lifecycle.LiveData
import com.example.tracker.home.common.data.daos.WorkoutDao
import com.example.tracker.home.common.data.entities.XWorkout

/*
database.unitsDao(),
database.exercisesDao(),
database.workoutsDao(),
database.setsDao(),
database.usersDao()

*/


class AppRepository(private val workoutsDao: WorkoutDao){
    val allWorkouts : LiveData<List<XWorkout>> = workoutsDao.getAll();

    fun getWorkoutsByUser(userId: Int): LiveData<List<XWorkout>>{
        return workoutsDao.getWorkoutsByUser(userId);
    }
}