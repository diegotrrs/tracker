package com.example.tracker.home.workouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.home.common.data.entities.XWorkout

class WorkoutsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WorkoutsListAdapter.WorkoutsViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var workouts = emptyList<XWorkout>();

    inner class WorkoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item : TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val itemView = inflater.inflate(R.layout.workouts_list_item, parent, false)
        return WorkoutsViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        val current = workouts[position]
        holder.item.text = current.durationMs.toString()
    }

    internal fun setWorkouts(XWorkouts: List<XWorkout>) {
        this.workouts = XWorkouts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.workouts.size
    }


}