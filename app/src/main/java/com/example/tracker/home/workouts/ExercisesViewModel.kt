package com.example.tracker.home.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.home.common.data.AppDatabase
import com.example.tracker.home.common.data.AppRepository
import com.example.tracker.home.common.data.entities.XUserAndWorkouts
import com.example.tracker.home.common.data.entities.XUserAndWorkoutsAndExercises
import com.example.tracker.home.common.data.entities.XWorkout
import com.example.tracker.home.common.data.entities.XWorkoutAndExercises

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository
    val workouts: LiveData<List<XUserAndWorkoutsAndExercises>>

    init {
        var userDao = AppDatabase.getDatabase(application, viewModelScope).usersDao();
        appRepository = AppRepository(userDao)
        workouts = appRepository.workouts
    }
}