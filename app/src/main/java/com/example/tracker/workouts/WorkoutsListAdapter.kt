package com.example.tracker.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.XWorkoutAndExercises

class WorkoutsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WorkoutsListAdapter.WorkoutsViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var workoutsAndExercises = emptyList<XWorkoutAndExercises>();

    inner class WorkoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName : TextView = itemView.findViewById(R.id.nameTextView)
        val exercisesText : TextView = itemView.findViewById(R.id.exercisesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val itemView = inflater.inflate(R.layout.workouts_list_item, parent, false)
        return WorkoutsViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        val current = workoutsAndExercises[position]
        var exercises = ""
        current.exercises.forEach {
            println("Exercise ${it}")
            exercises += "\n ${it.getUnit().name} \n"
            it.sets.forEach {
                exercises += " \t> ${it.weight}  ${it.reps}\n"
            }

        }
        with(holder){
            workoutName.text =  current.workout.durationMs.toString()
            exercisesText.text =  exercises
        }

    }

    internal fun setWorkoutsAndExercises(workoutsAndExercises: List<XWorkoutAndExercises>) {
        this.workoutsAndExercises = workoutsAndExercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.workoutsAndExercises.size
    }


}