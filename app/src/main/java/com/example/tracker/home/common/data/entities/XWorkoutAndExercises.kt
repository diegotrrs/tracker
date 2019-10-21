package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation

class XWorkoutAndExercises(
    @Embedded
    var workout: XWorkout,

    @Relation(
        parentColumn = "id",
        entityColumn = "workoutId",
        entity = XExercise::class

    )
    var exercises: List<XExercise> = listOf()

)