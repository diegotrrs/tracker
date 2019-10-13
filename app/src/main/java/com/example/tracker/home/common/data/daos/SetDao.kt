package com.example.tracker.home.common.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.home.common.data.entities.XSet


@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(xSet: XSet): Long

    @Query("DELETE FROM sets")
    suspend fun deleteAll()

    @Query("SELECT * FROM sets")
    fun getAll(): LiveData<List<XSet>>
}

