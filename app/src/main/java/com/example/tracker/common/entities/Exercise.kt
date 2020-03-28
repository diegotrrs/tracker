package com.example.tracker.common.entities

import androidx.room.*


@Entity(tableName = "units")
data class Exercise(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name") val name: String
){
    constructor(name: String= "") : this(0, name)
}

