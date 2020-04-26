package com.example.tracker.exercises

import com.example.tracker.common.entities.Exercise

interface ExerciseListListener {
    fun onCreateExercise(name: String): Unit
    fun onExerciseSelected(exercise: Exercise): Unit
    fun onDeleteExercise(exercise: Exercise): Unit
}
