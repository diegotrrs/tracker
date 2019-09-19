package com.example.tracker.home.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R

class WorkoutsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WorkoutsListAdapter.WorkoutsViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var workouts = emptyList<Exercise>();

    inner class WorkoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item : TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val itemView = inflater.inflate(R.layout.workouts_list_item, parent, false)
        return WorkoutsViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        val current = workouts[position]
        holder.item.text = current.name
    }

    internal fun setWorkouts(exercises: List<Exercise>) {
        this.workouts = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.workouts.size
    }


}