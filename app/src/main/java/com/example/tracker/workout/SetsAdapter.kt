package com.example.tracker.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.entities.WSet
import com.example.tracker.databinding.SetsListItemBinding
import com.example.tracker.workout.SetsAdapter.ViewHolder

class SetsAdapter internal constructor(var context: Context, val entryId: Long, val listener: SetsListener) : RecyclerView.Adapter<ViewHolder>() {
    private var sets = emptyList<WSet>()

    inner class ViewHolder(var binding: SetsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnLongClickListener {
                EditSetDialog.getInstance(binding.set, listener).show((context as AppCompatActivity).supportFragmentManager, EditSetDialog.TAG)
                true;
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.sets_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return sets.size
    }

    internal fun setSets(sets: List<WSet>) {
        this.sets = sets
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.set = sets[position];
    }
}