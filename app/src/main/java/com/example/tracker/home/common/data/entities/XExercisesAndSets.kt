package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation

class XExercisesAndSets {
    @Embedded
    var exercise: XExercise? = null;

    @Relation(parentColumn = "id", entityColumn = "exerciseId")
    var sets: List<XSet>? = null;
}