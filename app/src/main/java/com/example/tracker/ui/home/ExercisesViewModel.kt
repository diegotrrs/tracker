package com.example.tracker.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.ui.common.TrackerDatabase
import com.example.tracker.ui.common.TrackerRepository
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TrackerRepository;
    val allExercises: LiveData<List<Exercise>>

    init {
       var exerciseDao = TrackerDatabase.getDatabase(application, viewModelScope).exercisesDao();
       repository = TrackerRepository(exerciseDao)
        allExercises = repository.allExercises
    }

    fun insertExercise(exercise: Exercise) = viewModelScope.launch { repository.insertExercise(exercise) }

}