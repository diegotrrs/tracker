package com.example.tracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R

class HomeFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)


        val adapter = ExerciseListAdapter(requireContext() )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel::class.java)

        exercisesViewModel.allExercises.observe(this, Observer { exercises ->
            exercises?.let{ adapter.setExercises(it)}
        })
        return root;

    }
}