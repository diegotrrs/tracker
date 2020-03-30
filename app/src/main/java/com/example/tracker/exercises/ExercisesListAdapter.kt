package com.example.tracker.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.Exercise
import com.example.tracker.common.entities.WorkoutAndEntries

class ExercisesListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ExercisesListAdapter.ExercisesViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var exercises = emptyList<Exercise>();

    inner class ExercisesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName : TextView = itemView.findViewById(R.id.exerciseNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val itemView = inflater.inflate(R.layout.exercises_list_item, parent, false)
        return ExercisesViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        val current = exercises[position]
        with(holder){
            exerciseName.text =  current.name
        }
    }

    internal fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.exercises.size
    }
}