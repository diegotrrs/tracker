package com.example.tracker.home.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.AppDatabase
import com.example.tracker.common.AppRepository
import com.example.tracker.common.entities.XUserAndWorkoutsAndExercises

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository
    val workouts: LiveData<List<XUserAndWorkoutsAndExercises>>

    init {
        var userDao = AppDatabase.getDatabase(application, viewModelScope).usersDao();
        appRepository = AppRepository(userDao)
        workouts = appRepository.workouts
    }
}