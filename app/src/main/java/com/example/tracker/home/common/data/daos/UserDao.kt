package com.example.tracker.home.common.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.home.common.data.entities.XUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XUser: XUser): Long

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<XUser>>
}
