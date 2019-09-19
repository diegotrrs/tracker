package com.example.tracker.home.workouts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "targetMilliseconds") val targetMilliseconds: Long
)
