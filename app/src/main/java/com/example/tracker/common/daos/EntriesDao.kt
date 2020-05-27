package com.example.tracker.common.daos

import com.example.tracker.common.entities.Entry


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EntriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Entry: Entry): Long

    @Query("DELETE FROM entries")
    suspend fun deleteAll()

    @Query( "DELETE FROM entries where id=:entryId")
    suspend fun deleteEntry(entryId: Long)

    @Query("SELECT * FROM entries")
    fun getAll(): LiveData<List<Entry>>

}
