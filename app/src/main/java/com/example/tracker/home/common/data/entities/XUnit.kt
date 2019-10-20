package com.example.tracker.home.common.data.entities

import androidx.room.*


@Entity(tableName = "units")
data class XUnit(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "username") val name: String
){
    constructor(name: String= "") : this(0, name)
}

