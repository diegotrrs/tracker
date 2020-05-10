package com.example.tracker.workout


import androidx.lifecycle.*

class WorkoutViewModel(workoutRepository: WorkoutRepository, workoutId: Long) : ViewModel() {
    val workout = workoutRepository.getWorkoutById(workoutId)
}

