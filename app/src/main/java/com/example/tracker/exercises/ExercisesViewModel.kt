package com.example.tracker.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.AppDatabase
import com.example.tracker.common.AppRepository
import com.example.tracker.common.entities.Exercise
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository
    val exercises: LiveData<List<Exercise>>

    init {
        var usersDao = AppDatabase.getDatabase(application, viewModelScope).usersDao();
        var exercisesDao = AppDatabase.getDatabase(application, viewModelScope).exercisesDao();
        appRepository = AppRepository(usersDao, exercisesDao)
        exercises = appRepository.exercises
    }

    fun createExercise(exerciseName: String){
        viewModelScope.launch {
            appRepository.createExercise(exerciseName)
        }
    }

    fun deleteExercise(exerciseId: Long){
        viewModelScope.launch {
            appRepository.deleteExercise(exerciseId)
        }
    }
}