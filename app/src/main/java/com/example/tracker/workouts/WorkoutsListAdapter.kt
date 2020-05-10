package com.example.tracker.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.WorkoutAndEntries
import com.example.tracker.databinding.WorkoutsListItemBinding

class WorkoutsListAdapter internal constructor() : RecyclerView.Adapter<WorkoutsListAdapter.ViewHolder>(){
    private var workoutsAndEntries = emptyList<WorkoutAndEntries>();

    inner class ViewHolder(val binding: WorkoutsListItemBinding):  RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnClickListener(View.OnClickListener {
                val direction = WorkoutsFragmentDirections.actionWorkoutsToWorkout(binding!!.workoutAndEntries!!.workout.id.toString())
                it!!.findNavController().navigate(direction)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.workouts_list_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = workoutsAndEntries[position]
        holder.binding.apply {
            workoutAndEntries = current
            entriesTextView.text =  buildWorkoutEntries(current)
        }
    }

    private fun buildWorkoutEntries(current: WorkoutAndEntries): String {
        var entries = ""
        current.entries.forEach {
            println("Entry ${it}")
            entries += "\n ${it.getExercise().name} \n"
            it.sets.forEach {
                entries += " \t> ${it.weight}  ${it.reps}\n"
            }

        }
        return entries
    }

    internal fun setWorkoutsAndEntries(workoutsAndEntries: List<WorkoutAndEntries>) {
        this.workoutsAndEntries = workoutsAndEntries
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.workoutsAndEntries.size
    }
}