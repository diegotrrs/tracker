package com.example.tracker.common.entities

import androidx.room.Embedded
import androidx.room.Relation


class UserAndWorkoutsAndEntries(
    @Embedded
    var user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
        entity = Workout::class
    )
    var workoutsAndEntries: List<WorkoutAndEntries> = listOf()
)

