package com.example.tracker.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.WorkoutsFragmentBinding
//import com.example.tracker.home.HomeFragmentDirections
//import com.example.tracker.home.workouts

class WorkoutsFragment : Fragment() {

    private lateinit var workoutsViewModel: WorkoutsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel::class.java)

        var binding = DataBindingUtil.inflate<WorkoutsFragmentBinding>(
            inflater,
            R.layout.workouts_fragment,
            container,
            false
        )
            .apply {

                this.createNewWorkoutButton.setOnClickListener {
                    it.findNavController().navigate(R.id.action_workouts_to_newworkout)
                }

                viewModel = workoutsViewModel
                lifecycleOwner = viewLifecycleOwner
               /* callback = object : Callback {
                    override fun action() {
                        println("ACTION!")
                        val direction = HomeFragmentDirections.actionNavigationHomeToNavigationNewworkout()
                        requireActivity().findNavController().navigate(direction)

                    }
                }*/



                val adapter = WorkoutsListAdapter(requireContext())
                recyclerView.adapter = adapter


                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                this.lifecycleOwner?.let {
                    workoutsViewModel.workouts.observe(it, Observer { workouts ->
                        workouts?.let {
                            if (it.size > 0) {
                                adapter.setWorkoutsAndEntries(it[0].workoutsAndEntries)
                            }
                        }
                    })
                }

            }

        return binding.root
    }

    interface Callback {
        fun action()
    }


    fun onCreateViewOld(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.workouts_fragment, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)


        val adapter = WorkoutsListAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel::class.java)

        workoutsViewModel.workouts.observe(this, Observer { workouts ->
            workouts?.let {
                println("************************ Fragment UserAndWorkoutsAndEntries:  ${it.size} UserAndWorkouts")
                if (it.size > 0) {
                    println("${it[0].user} User")
                    println("${it[0].workoutsAndEntries.size} workoutsAndEntries")
                    adapter.setWorkoutsAndEntries(it[0].workoutsAndEntries)
                }
            }
        })
        return root;

    }
}