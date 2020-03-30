package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.common.entities.WSet

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(set: WSet): Long

    @Query("DELETE FROM sets")
    suspend fun deleteAll()

    @Query("SELECT * FROM sets")
    fun getAll(): LiveData<List<WSet>>
}

