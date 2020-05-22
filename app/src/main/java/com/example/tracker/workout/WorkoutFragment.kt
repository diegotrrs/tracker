package com.example.tracker.workout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.InjectorUtils
import com.example.tracker.common.entities.WSet
import com.example.tracker.databinding.WorkoutFragmentBinding
import kotlinx.android.synthetic.main.workout_fragment.*


class WorkoutFragment : Fragment() {

    private val args: WorkoutFragmentArgs by navArgs()

    private val workoutViewModel: WorkoutViewModel by viewModels {
        var workoutId: Long = args.workoutId.toLong();
        InjectorUtils.provideWorkoutViewModelFactory(requireContext(), workoutId)
    }

    companion object {
        const val SELECTED_EXERCISE = "selectedExercise"
    }

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        var binding = DataBindingUtil.inflate<WorkoutFragmentBinding>(
            inflater, R.layout.workout_fragment, container, false
        ).apply {
            appCompatActivity.setSupportActionBar(toolbar)
            lifecycleOwner = viewLifecycleOwner
            viewModel = workoutViewModel

            val adapter = WorkoutEntriesAdapter(requireContext(), object : SetsListener {
                override fun onCreateSet(entryId: Long, weight: Double, reps: Short) {
                    viewModel!!.createSet(entryId, weight, reps)
                    println("On  create set entryId${entryId} weight${weight}  reps${reps} ");
                }

                override fun onEditSet(set: WSet, weight: Double, reps: Short) {
                    viewModel!!.editSet(set, weight, reps)
                    println("On  edit set set ${set.id} ${set.entryId} weight${weight}  reps${reps} ");
                }
            })
            entriesRecyclerView.adapter = adapter
            entriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            workoutViewModel.workoutAndEntries.observe(viewLifecycleOwner, Observer { workoutAndEntries ->
                if (workoutAndEntries.entries.isNotEmpty()) {
                    adapter.setEntriesAndSets(workoutAndEntries.entries)
                }

            })

            this.addExerciseButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_workout_to_exercises)
            }
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
        if (menuItem.getItemId().equals(R.id.action_save)) {
            // println("Save workout"  binding.exerciseNameTextView.text.toString())
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

}
