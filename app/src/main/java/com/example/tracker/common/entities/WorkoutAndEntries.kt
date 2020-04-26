package com.example.tracker.common.entities

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutAndEntries(
    @Embedded
    var workout: Workout,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId",
        entity = Entry::class

    )
    var entries: List<EntryAndSets> = listOf()

)