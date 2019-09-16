package com.example.tracker.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R

class ExerciseListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var exercises = emptyList<Exercise>();

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item : TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = inflater.inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = exercises[position]
        holder.item.text = current.name
    }

    internal fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.exercises.size
    }


}