package com.example.tracker.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.AppDatabase
import com.example.tracker.common.AppRepository
import com.example.tracker.common.TrackerApp
import com.example.tracker.common.entities.EntryAndSets
import com.example.tracker.common.entities.Workout
import com.example.tracker.common.entities.WorkoutAndEntries
import kotlinx.coroutines.launch


class WorkoutViewModel (application: Application) : AndroidViewModel(application) {
    //private val appRepository: AppRepository
    // val workouts: LiveData<List<UserAndWorkoutsAndEntries>>

    val entries: MutableLiveData<List<EntryAndSets>>

    //lateinit var workout: MutableLiveData<Workout>

    init {
       /* var usersDao = AppDatabase.getDatabase(application, viewModelScope).usersDao();
        var exercisesDao = AppDatabase.getDatabase(application, viewModelScope).exercisesDao();
        appRepository = AppRepository(usersDao, exercisesDao)
        workouts = appRepository.workouts */
        var workoutDao = AppDatabase.getDatabase(application, viewModelScope).workoutsDao();
        entries = MutableLiveData()
        //appRepository = AppRepository(workoutDao)
    }

    fun createWorkout(name: String){
        viewModelScope.launch {
           // appRepository.createWorkout(Workout(name, TrackerApp().userId))
        }
    }

   /* fun createExercise(exerciseName: String){
        viewModelScope.launch {
            appRepository.createExercise(exerciseName)
        }
    }*/

}
