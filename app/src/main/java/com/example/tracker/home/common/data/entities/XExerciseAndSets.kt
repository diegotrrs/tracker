package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation


class XExerciseAndSets(
    @Embedded
    var exercise: XExercise,


    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId",
        entity = XSet::class
    )
    var sets : List<XSet> = listOf()

)