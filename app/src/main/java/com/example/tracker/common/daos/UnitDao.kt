package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.XUnit


@Dao
interface UnitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XUnit: XUnit): Long

    @Query("DELETE FROM units")
    suspend fun deleteAll()

    @Query("SELECT * FROM units")
    fun getAll(): LiveData<List<XUnit>>
}

