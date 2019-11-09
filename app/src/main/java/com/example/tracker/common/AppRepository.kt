package com.example.tracker.common

import androidx.lifecycle.LiveData
import com.example.tracker.common.daos.UserDao
import com.example.tracker.common.entities.XUserAndWorkoutsAndExercises

/*
database.unitsDao(),
database.exercisesDao(),
database.workoutsDao(),
database.setsDao(),
database.usersDao()

*/


class AppRepository(private val userDao: UserDao){
    val workouts : LiveData<List<XUserAndWorkoutsAndExercises>> = userDao.getUser("jctorres");
}