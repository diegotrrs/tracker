package com.example.tracker.home.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.home.common.data.AppDatabase
import com.example.tracker.home.common.data.AppRepository
import com.example.tracker.home.common.data.entities.XWorkout

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository
    val allWorkouts: LiveData<List<XWorkout>>

    init {
        var workoutsDao = AppDatabase.getDatabase(application, viewModelScope).workoutsDao();
        appRepository = AppRepository(workoutsDao)
        allWorkouts = appRepository.allWorkouts
    }
}