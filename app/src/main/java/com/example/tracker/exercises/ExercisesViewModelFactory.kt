package com.example.tracker.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExercisesViewModelFactory(
    private val repository: ExercisesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExercisesViewModel(repository) as T
    }
}