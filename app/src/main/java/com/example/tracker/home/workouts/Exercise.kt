package com.example.tracker.ui.home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(@PrimaryKey @ColumnInfo(name = "name") val name: String)
