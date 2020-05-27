package com.example.tracker.workout

import com.example.tracker.common.entities.WSet

interface WorkoutEntriesListener {
    fun onCreateWorkoutEntry(exerciseId: Long);
    fun onDeleteWorkoutEntry(entryId: Long);
}