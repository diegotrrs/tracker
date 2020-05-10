package com.example.tracker.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tracker.common.TrackerApp

class WorkoutsViewModelFactory(
    private val repository: WorkoutsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorkoutsViewModel(repository, TrackerApp().userId) as T
    }
}