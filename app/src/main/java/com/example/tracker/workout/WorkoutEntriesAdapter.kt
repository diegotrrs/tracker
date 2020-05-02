package com.example.tracker.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.EntryAndSets
import kotlinx.android.synthetic.main.workout_entries_list_item.view.*

class WorkoutEntriesAdapter internal constructor(context: Context): RecyclerView.Adapter<WorkoutEntriesAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entriesAndSets = emptyList<EntryAndSets>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.workout_entries_list_item, parent, false)
        return ViewHolder(itemView )
    }

    override fun getItemCount(): Int {
        return entriesAndSets.size
    }

    internal fun setEntriesAndSets(entriesAndSets: List<EntryAndSets>){
        this.entriesAndSets = entriesAndSets
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = entriesAndSets.get(position)
        with(holder){
            holder.exerciseName.text = current.getExercise().name
        }
    }
}