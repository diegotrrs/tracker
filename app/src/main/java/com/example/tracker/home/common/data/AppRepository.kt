package com.example.tracker.home.common.data

import androidx.lifecycle.LiveData
import com.example.tracker.home.common.data.daos.UserDao
import com.example.tracker.home.common.data.entities.XUserAndWorkoutsAndExercises

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