package com.example.tracker.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.WorkoutAndEntries

class WorkoutsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WorkoutsListAdapter.WorkoutsViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var workoutsAndEntries = emptyList<WorkoutAndEntries>();

    inner class WorkoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName : TextView = itemView.findViewById(R.id.nameTextView)
        val entriesText : TextView = itemView.findViewById(R.id.entriesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val itemView = inflater.inflate(R.layout.workouts_list_item, parent, false)
        return WorkoutsViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        val current = workoutsAndEntries[position]
        var entries = ""
        current.entries.forEach {
            println("Entry ${it}")
            entries += "\n ${it.getUnit().name} \n"
            it.sets.forEach {
                entries += " \t> ${it.weight}  ${it.reps}\n"
            }

        }
        with(holder){
            workoutName.text =  current.workout.durationMs.toString()
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