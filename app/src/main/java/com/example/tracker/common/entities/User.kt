package com.example.tracker.common.entities

import androidx.room.*


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "username") val username: String

) {
    @Ignore
    constructor(username: String= "" ) : this(0, username)
}