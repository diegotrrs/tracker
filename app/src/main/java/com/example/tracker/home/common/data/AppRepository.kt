package com.example.tracker.home.common.data

import androidx.lifecycle.LiveData
import com.example.tracker.home.common.data.daos.UserDao
import com.example.tracker.home.common.data.daos.WorkoutDao
import com.example.tracker.home.common.data.entities.XUserAndWorkouts
import com.example.tracker.home.common.data.entities.XWorkout
import com.example.tracker.home.common.data.entities.XWorkoutAndExercises

/*
database.unitsDao(),
database.exercisesDao(),
database.workoutsDao(),
database.setsDao(),
database.usersDao()

*/


class AppRepository(private val userDao: UserDao){
    val workouts : LiveData<List<XUserAndWorkouts>> = userDao.getUserWorkouts("cfreese");
}