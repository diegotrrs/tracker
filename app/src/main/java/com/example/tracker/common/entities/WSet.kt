package com.example.tracker.common.entities

import androidx.room.*

@Entity(
    tableName = "sets",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Entry::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("entryId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices = arrayOf(Index(value = ["entryId"]))
)
data class WSet(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0,
    @ColumnInfo(name = "weight")
    var weight: Double = 0.0,
    @ColumnInfo(name = "reps")
    var reps: Short = 0,
    @ColumnInfo(name = "entryId")
    var entryId: Long = 0
)

