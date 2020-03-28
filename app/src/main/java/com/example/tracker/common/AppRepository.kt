package com.example.tracker.common

import androidx.lifecycle.LiveData
import com.example.tracker.common.daos.UserDao
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

/*
database.unitsDao(),
database.entriesDao(),
database.workoutsDao(),
database.setsDao(),
database.usersDao()

*/


class AppRepository(private val userDao: UserDao){
    val workouts : LiveData<List<UserAndWorkoutsAndEntries>> = userDao.getUser("jctorres");
}