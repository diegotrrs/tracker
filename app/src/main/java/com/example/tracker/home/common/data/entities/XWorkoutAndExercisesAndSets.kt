package com.example.tracker.home.common.data.entities

import androidx.room.Embedded
import androidx.room.Relation

class XWorkoutAndExercisesAndSets {
    @Embedded
    var workout: XWorkout? = null;

    @Relation(parentColumn = "userId", entityColumn = "id", entity = XUser::class)
    var user: List<XUser>? = null;

    @Relation(parentColumn = "id", entityColumn = "workoutId", entity = XExercise::class)
    var exercises: List<XExercisesAndSets>? = null;



}