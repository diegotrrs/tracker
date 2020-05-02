package com.example.tracker.workout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.WorkoutFragmentBinding
import kotlinx.android.synthetic.main.workout_fragment.*


class WorkoutFragment : Fragment() {

    companion object {
        const val SELECTED_EXERCISE = "selectedExercise"
    }



    private lateinit var viewModel: WorkoutViewModel
    private lateinit var binding: WorkoutFragmentBinding
    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    private val args: WorkoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        binding = DataBindingUtil.inflate<WorkoutFragmentBinding>(
            inflater, R.layout.workout_fragment, container, false
        ).apply {
            appCompatActivity.setSupportActionBar(toolbar)
            this.addExerciseButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_workout_to_exercises)
            }

            lifecycleOwner = viewLifecycleOwner

            val adapter = WorkoutEntriesAdapter(requireContext())
            entriesRecyclerView.adapter = adapter

            entriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            /* this.lifecycleOwner?.let {
                 viewModel!!.entries.observe(it, Observer { entries ->
                     entries?.let {
                         if (it.size > 0) {
                             adapter.setEntriesAndSets(entries);
                         }
                     }
                 })
             }*/
        };
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController(this);
        // We use a String here, but any type that can be put in a Bundle is supported
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Long>(SELECTED_EXERCISE)
            ?.observe(viewLifecycleOwner, Observer { exercise ->
                println("Exercise select Workout Fragment");
                println(exercise);
            })
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() === R.id.action_save) {
            println("Save workout" + binding.exerciseNameTextView.text.toString())
            // viewModel.createWorkout(binding.exerciseNameTextView.text.toString())
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.workout_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitle(getString(R.string.workout))
    }

    interface Callback {
        fun action()
    }

}
