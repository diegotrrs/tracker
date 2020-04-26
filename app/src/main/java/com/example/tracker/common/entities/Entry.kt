package com.example.tracker.common.entities

import androidx.room.*


@Entity(
    tableName = "entries",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("workoutId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices =[ Index("workoutId"), Index("exerciseId")]
)
data class Entry(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "workoutId")
    var workoutId: Long = 0,
    @ColumnInfo(name = "exerciseId")
    var exerciseId: Long
){
    @Ignore
    constructor(workoutId: Long, exerciseId: Long) : this(0, workoutId, exerciseId)
}
