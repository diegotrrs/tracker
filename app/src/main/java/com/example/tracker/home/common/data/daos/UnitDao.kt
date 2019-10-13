package com.example.tracker.home.common.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.home.common.data.entities.XUnit


@Dao
interface UnitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XUnit: XUnit): Long

    @Query("DELETE FROM units")
    suspend fun deleteAll()

    @Query("SELECT * FROM units")
    fun getAll(): LiveData<List<XUnit>>
}

