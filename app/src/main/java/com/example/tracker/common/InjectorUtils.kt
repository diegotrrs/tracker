package com.example.tracker.common

import android.content.Context
import com.example.tracker.exercises.ExercisesRepository
import com.example.tracker.exercises.ExercisesViewModelFactory
import com.example.tracker.workout.SetsRepository
import com.example.tracker.workout.WorkoutRepository
import com.example.tracker.workout.WorkoutViewModelFactory
import com.example.tracker.workouts.WorkoutsRepository
import com.example.tracker.workouts.WorkoutsViewModelFactory

object InjectorUtils {

    private fun getWorkoutsRepository(context: Context): WorkoutsRepository {
        var database = AppDatabase.getInstance(context.applicationContext);
        return WorkoutsRepository.getInstance(database.workoutsDao(), database.usersDao())
    }

    private fun getExercisesRepository(context: Context): ExercisesRepository {
        var database = AppDatabase.getInstance(context.applicationContext);
        return ExercisesRepository.getInstance(database.exercisesDao())
    }

    private fun getWorkoutRepository(context: Context): WorkoutRepository {
        var database = AppDatabase.getInstance(context.applicationContext);
        return WorkoutRepository.getInstance(database.workoutsDao())
    }

    private fun getSetsRepository(context: Context): SetsRepository {
        var database = AppDatabase.getInstance(context.applicationContext);
        return SetsRepository.getInstance(database.setsDao())
    }

    fun provideWorkoutsViewModelFactory(context: Context): WorkoutsViewModelFactory {
        return WorkoutsViewModelFactory(getWorkoutsRepository(context))
    }

    fun provideExercisesViewModelFactory(context: Context): ExercisesViewModelFactory {
        return ExercisesViewModelFactory(getExercisesRepository(context))
    }

    fun provideWorkoutViewModelFactory(context: Context, workoutId: Long): WorkoutViewModelFactory {
        return WorkoutViewModelFactory(getWorkoutRepository(context), getSetsRepository(context), workoutId)
    }
}