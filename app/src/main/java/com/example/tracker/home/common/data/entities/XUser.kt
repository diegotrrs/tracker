package com.example.tracker.home.common.data.entities

import androidx.room.*


@Entity(tableName = "users")
data class XUser(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "username") val username: String

) {
    @Ignore
    constructor(username: String= "" ) : this(0, username)
}