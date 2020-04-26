package com.example.tracker.common.entities

import androidx.room.Embedded
import androidx.room.Relation


class EntryAndSets(
    @Embedded
    var entry: Entry,


    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "id",
        entity = Exercise::class
    )
    var units: List<Exercise> = listOf(),

    @Relation(
        parentColumn = "id",
        entityColumn = "entryId",
        entity = WSet::class
    )
    var sets : List<WSet> = listOf()

) {

    fun getUnit(): Exercise {
        return units[0];
    }
}