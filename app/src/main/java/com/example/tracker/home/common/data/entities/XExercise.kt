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
        )
    ),
    indices = arrayOf(Index(value = ["workoutId"]))
)
data class XExercise(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "workoutId")
    var workoutId: Long = 0,
    @ColumnInfo(name = "name")
    var name: String = ""
){
    @Ignore
    constructor(workoutId: Long, name: String) : this(0, workoutId, name)
}
