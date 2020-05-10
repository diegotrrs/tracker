package com.example.tracker.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExercisesViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {
    val exercises = exercisesRepository.getExercises()

    fun createExercise(exerciseName: String) {
        viewModelScope.launch {
            exercisesRepository.createExercise(exerciseName)
        }
    }

    fun deleteExercise(exerciseId: Long) {
        viewModelScope.launch {
            exercisesRepository.deleteExercise(exerciseId)
        }
    }
}