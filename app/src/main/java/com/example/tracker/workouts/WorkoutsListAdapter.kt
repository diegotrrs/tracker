package com.example.tracker.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.WorkoutAndEntries

class WorkoutsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WorkoutsListAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var workoutsAndEntries = emptyList<WorkoutAndEntries>();

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName : TextView = itemView.findViewById(R.id.nameTextView)
        val entriesText : TextView = itemView.findViewById(R.id.entriesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.workouts_list_item, parent, false)
        return ViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = workoutsAndEntries[position]
        var entries = ""
        current.entries.forEach {
            println("Entry ${it}")
            entries += "\n ${it.getExercise().name} \n"
            it.sets.forEach {
                entries += " \t> ${it.weight}  ${it.reps}\n"
            }

        }
        with(holder){
            workoutName.text =  current.workout.name
            entriesText.text =  entries
        }

    }

    internal fun setWorkoutsAndEntries(workoutsAndEntries: List<WorkoutAndEntries>) {
        this.workoutsAndEntries = workoutsAndEntries
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.workoutsAndEntries.size
    }


}