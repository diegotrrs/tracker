package com.example.tracker.workout


import androidx.lifecycle.*

class WorkoutViewModel(workoutRepository: WorkoutRepository, workoutId: Long) : ViewModel() {
    val workoutAndEntries = workoutRepository.getWorkoutById(workoutId)
}

