package com.example.tracker.common.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.common.entities.Workout
import com.example.tracker.common.entities.WorkoutAndEntries

@Dao
interface WorkoutsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Workout: Workout): Long

    @Query("DELETE FROM workouts")
    suspend fun deleteAll()

    @Query("SELECT * FROM workouts WHERE id =:workoutId")
    fun getWorkoutById(workoutId: Long): LiveData<List<WorkoutAndEntries>>

    /*@Query("SELECT * FROM workouts WHERE durationMs =:duration")
    fun findByWorkoutDuration(duration: Long): LiveData<List<WorkoutAndEntries>>

    @Query("SELECT * FROM workouts " +
            "INNER JOIN workoutEntries ON workouts.id = workoutEntries.workoutId " +
            "INNER JOIN WSets ON workoutEntries.id = WSets.workoutEntryId " +
            "WHERE workouts.userId=:userId")
    fun getWorkoutsByUser(userId: Int): LiveData<List<Workout>>*/

    /*@Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun loadWorkout(workoutId: Long): Single<TripAndListsAndListItems>





    @Query("SELECT workouts.*,users.* FROM workouts " +
            "INNER JOIN users ON workouts.userId = users.id " +
            "WHERE workouts.userId=:userId")
    fun getWorkoutsByUser(userId: Int): LiveData<List<Workout>>*/


}
