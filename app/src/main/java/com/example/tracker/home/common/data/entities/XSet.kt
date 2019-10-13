package com.example.tracker.home.common.data.entities

import androidx.room.*

@Entity(
    tableName = "sets",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = XExercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices = arrayOf(Index(value = ["exerciseId"]))
)
data class XSet(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "weight")
    var weight: Long = 0,
    @ColumnInfo(name = "reps")
    var reps: Long = 0,
    @ColumnInfo(name = "exerciseId")
    var exerciseId: Long = 0
) {
    @Ignore
    constructor(weight: Long, reps: Long, exerciseId: Long) : this(
        0,
        weight,
        reps,
        exerciseId
    )
}

