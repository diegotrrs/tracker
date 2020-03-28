package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.Exercise


@Dao
interface UnitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Exercise: Exercise): Long

    @Query("DELETE FROM units")
    suspend fun deleteAll()

    @Query("SELECT * FROM units")
    fun getAll(): LiveData<List<Exercise>>
}

