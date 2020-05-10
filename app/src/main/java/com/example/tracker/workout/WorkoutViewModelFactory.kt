package com.example.tracker.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tracker.common.TrackerApp
import com.example.tracker.workouts.WorkoutsRepository
import com.example.tracker.workouts.WorkoutsViewModel

class WorkoutViewModelFactory(
    private val repository: WorkoutRepository,
    private val workoutId: Long
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorkoutViewModel(repository, workoutId) as T
    }
}