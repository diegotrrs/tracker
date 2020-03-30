package com.example.tracker.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.ExercisesFragmentBinding


class ExercisesFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel::class.java)

        var binding = DataBindingUtil.inflate<ExercisesFragmentBinding>(
            inflater,
            R.layout.exercises_fragment,
            container,
            false
        )
            .apply {

                viewModel = exercisesViewModel
                lifecycleOwner = viewLifecycleOwner

                val adapter = ExercisesListAdapter(requireContext())
                recyclerView.adapter = adapter

                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                this.lifecycleOwner?.let {
                    exercisesViewModel.exercises.observe(it, Observer { exercises ->
                        adapter.setExercises(exercises)
                    })
                }

            }

        return binding.root
    }

    interface Callback {
        fun action()
    }
}