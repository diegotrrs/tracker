package com.example.tracker.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WorkoutViewModelFactory(
    private val workoutRepository: WorkoutRepository,
    private val setsRepository: SetsRepository,
    private val entriesRepository: EntriesRepository,
    private val workoutId: Long
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorkoutViewModel(workoutRepository, setsRepository, entriesRepository, workoutId) as T
    }
}