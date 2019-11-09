package com.example.tracker.common.entities

import androidx.room.Embedded
import androidx.room.Relation


class XUserAndWorkoutsAndExercises(
    @Embedded
    var user: XUser,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = XWorkout::class
    )
    var workoutsAndExercises: List<XWorkoutAndExercises> = listOf()
)

