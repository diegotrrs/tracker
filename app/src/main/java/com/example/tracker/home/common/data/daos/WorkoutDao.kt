package com.example.tracker.home.common.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker.home.common.data.entities.XWorkout
import com.example.tracker.home.common.data.entities.XWorkoutAndExercisesAndSets

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(XWorkout: XWorkout): Long

    @Query("DELETE FROM workouts")
    suspend fun deleteAll()

    //@Query("SELECT * FROM workouts")
    //fun getAll(): LiveData<List<XWorkout>>

    @Query("SELECT * FROM workouts")
    fun getAll(): LiveData<List<XWorkoutAndExercisesAndSets>>

    /*@Query("SELECT * FROM workouts " +
            "INNER JOIN workoutExercises ON workouts.id = workoutExercises.workoutId " +
            "INNER JOIN sets ON workoutExercises.id = sets.workoutExerciseId " +
            "WHERE workouts.userId=:userId")
    fun getWorkoutsByUser(userId: Int): LiveData<List<XWorkout>>*/

    /*@Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun loadWorkout(workoutId: Long): Single<TripAndListsAndListItems>*/




    @Query("SELECT workouts.*,users.* FROM workouts " +
            "INNER JOIN users ON workouts.userId = users.id " +
            "WHERE workouts.userId=:userId")
    fun getWorkoutsByUser(userId: Int): LiveData<List<XWorkout>>
}
