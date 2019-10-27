package com.example.tracker.home.common.data.entities

import androidx.room.*

@Entity(
    tableName = "workouts",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = XUser::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices = arrayOf(Index(value = ["userId"]))
)
data class XWorkout(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name="durationMs")
    var durationMs: Long = 0,
    @ColumnInfo(name="startedMs")
    var startedMs: Long = 0,
    @ColumnInfo(name="finishedMs")
    var finishedMs: Long = 0,
    @ColumnInfo(name = "userId")
    var userId: Long = 0
){
    @Ignore
    constructor( durationMs: Long, userId: Long) : this(0, durationMs,0,0, userId)


}
