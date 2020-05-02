package com.example.tracker.common.entities

import androidx.room.*

@Entity(
    tableName = "workouts",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices = arrayOf(Index(value = ["userId"]))
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name="name")
    var name: String = "",
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
    constructor( name: String, userId: Long) : this(0, name,0,0, 0, userId)


}
