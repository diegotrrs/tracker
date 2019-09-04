package com.example.tracker.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(var name: String, var description: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0;

}