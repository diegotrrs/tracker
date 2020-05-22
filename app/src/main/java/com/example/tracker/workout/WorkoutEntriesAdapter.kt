package com.example.tracker.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.EntryAndSets
import com.example.tracker.databinding.WorkoutEntriesListItemBinding
import com.example.tracker.workout.WorkoutEntriesAdapter.ViewHolder

class WorkoutEntriesAdapter internal constructor(var context: Context, val listener: SetsListener) : RecyclerView.Adapter<ViewHolder>() {
    private var entriesAndSets = emptyList<EntryAndSets>()

    inner class ViewHolder(var binding: WorkoutEntriesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.addSetButton.setOnClickListener(View.OnClickListener {
                displayCreateSetDialog(binding.entryAndSets!!.entry.exerciseId);
            })
        }
    }

    private fun displayCreateSetDialog(entryId: Long) {
        CreateSetDialog.getInstance(entryId, listener).show((context as AppCompatActivity).supportFragmentManager, CreateSetDialog.TAG)
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
        var current = entriesAndSets[position];
        println("current.entry.exerciseId")
        println(current.entry.exerciseId)
        var adapter = SetsAdapter(context, current.entry.exerciseId, listener)

        with(holder.binding) {
            entryAndSets = current
            setsRecyclerView.adapter = adapter
            setsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        adapter.setSets(current.sets)

    }
}