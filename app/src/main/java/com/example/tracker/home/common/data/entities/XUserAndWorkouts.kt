package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation


class XUserAndWorkouts(
    @Embedded
    var user: XUser,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = XWorkout::class
    )
    var workouts: List<XWorkout> = listOf()
)

