package com.example.tracker.workout


import androidx.lifecycle.*
import com.example.tracker.common.entities.WSet
import kotlinx.coroutines.launch

class WorkoutViewModel(workoutRepository: WorkoutRepository, private val setsRepository: SetsRepository, workoutId: Long) : ViewModel() {
    val workoutAndEntries = workoutRepository.getWorkoutById(workoutId)

    fun createSet(entryId: Long, weight: Double, reps: Short) {
        viewModelScope.launch {
            setsRepository.createSet(entryId, weight, reps)
        }
    }

    fun editSet(set: WSet, weight: Double, reps: Short) {
        viewModelScope.launch {
            set.reps = reps
            set.weight = weight
            setsRepository.updateSet(set)
        }
    }
}

