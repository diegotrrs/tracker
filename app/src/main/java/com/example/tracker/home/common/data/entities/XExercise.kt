package com.example.tracker.home.common.data.entities

import androidx.room.*


@Entity(
    tableName = "exercises",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = XWorkout::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("workoutId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = XUnit::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("unitId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices =[ Index("workoutId"), Index("unitId")]
)
data class XExercise(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "workoutId")
    var workoutId: Long = 0,
    @ColumnInfo(name = "unitId")
    var unitId: Long
){
    @Ignore
    constructor(workoutId: Long, unitId: Long) : this(0, workoutId, unitId)
}
