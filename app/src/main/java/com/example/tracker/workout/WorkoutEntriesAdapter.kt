package com.example.tracker.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.EntryAndSets
import com.example.tracker.databinding.WorkoutEntriesListItemBinding
import com.example.tracker.workout.WorkoutEntriesAdapter.ViewHolder
import kotlinx.android.synthetic.main.workout_entries_list_item.view.*

class WorkoutEntriesAdapter internal constructor(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var entriesAndSets = emptyList<EntryAndSets>()

    inner class ViewHolder(var binding: WorkoutEntriesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.workout_entries_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return entriesAndSets.size
    }

    internal fun setEntriesAndSets(entriesAndSets: List<EntryAndSets>) {
        this.entriesAndSets = entriesAndSets
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.entryAndSets = entriesAndSets.get(position);
    }
}