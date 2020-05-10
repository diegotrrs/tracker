package com.example.tracker.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.AppDatabase
import com.example.tracker.common.entities.UserAndWorkoutsAndEntries

class WorkoutsViewModel(val workoutsRepository: WorkoutsRepository, userId: Long) : ViewModel() {
    val workouts = workoutsRepository.getWorkouts(userId)


    fun getWorkoutsByUser(userId: Long): LiveData<List<UserAndWorkoutsAndEntries>> {
        return workoutsRepository.getWorkouts(userId);
    }
}