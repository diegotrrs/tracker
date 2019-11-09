package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation


class XExerciseAndSets(
    @Embedded
    var exercise: XExercise,


    @Relation(
        parentColumn = "unitId",
        entityColumn = "id",
        entity = XUnit::class
    )
    var units: List<XUnit> = listOf(),

    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId",
        entity = XSet::class
    )
    var sets : List<XSet> = listOf()

) {

    fun getUnit(): XUnit {
        return units[0];
    }
}