package com.example.tracker.home.common.data.entities

import androidx.room.*


@Entity(tableName = "users")
data class XUser(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name") val name: String

) {
    @Ignore
    constructor(name: String= "" ) : this(0, name)
}