package com.example.tracker.home.common.data.daos

import com.example.tracker.home.common.data.entities.XExercise


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XExercise: XExercise): Long

    @Query("DELETE FROM exercises")
    suspend fun deleteAll()

    @Query("SELECT * FROM exercises")
    fun getAll(): LiveData<List<XExercise>>

}
