package com.example.tracker.common.entities

import androidx.room.*


@Entity(
    tableName = "entries",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("entryId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("unitId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices =[ Index("entryId"), Index("unitId")]
)
data class Entry(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "entryId")
    var entryId: Long = 0,
    @ColumnInfo(name = "unitId")
    var unitId: Long
){
    @Ignore
    constructor(entryId: Long, unitId: Long) : this(0, entryId, unitId)
}
