package com.example.tracker.home.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.home.TrackerDatabase
import com.example.tracker.home.Repository
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository;
    val allExercises: LiveData<List<Exercise>>

    init {
       var exerciseDao = TrackerDatabase.getDatabase(application, viewModelScope).exercisesDao();
       repository = Repository(exerciseDao)
        allExercises = repository.allExercises
    }

    fun insertExercise(exercise: Exercise) = viewModelScope.launch { repository.insertExercise(exercise) }

}