package com.example.tracker.home.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R

class WorkoutsFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.workouts_fragment, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)


        val adapter = WorkoutsListAdapter(requireContext() )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel::class.java)

        exercisesViewModel.allWorkouts.observe(this, Observer { allWorkouts ->
            allWorkouts?.let{ adapter.setWorkouts(it)}
        })
        return root;

    }
}