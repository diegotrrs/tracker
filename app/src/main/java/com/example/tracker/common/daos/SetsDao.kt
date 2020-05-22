package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tracker.common.entities.WSet

@Dao
interface SetsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(set: WSet): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(set: WSet): Int

    @Query("DELETE FROM sets")
    suspend fun deleteAll()

    @Query("SELECT * FROM sets")
    fun getAll(): LiveData<List<WSet>>
}

