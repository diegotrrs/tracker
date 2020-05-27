package com.example.tracker.workout

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.workout_fragment.*


class WorkoutFragment : Fragment(), SetsListener, WorkoutEntriesListener {
    val args: WorkoutFragmentArgs by navArgs()

    private val workoutViewModel: WorkoutViewModel by viewModels {
        var workoutId: Long = args.workoutId.toLong();
        InjectorUtils.provideWorkoutViewModelFactory(requireContext(), workoutId)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {
        const val SELECTED_EXERCISE = "selectedExercise"
    }

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    inline val isCreationMode: Boolean get() = (args.workoutId === "-1")

    private lateinit var binding: WorkoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        println(" workoutId parameter args.workoutId " + args.workoutId);
        binding = DataBindingUtil.inflate<WorkoutFragmentBinding>(
            inflater, R.layout.workout_fragment, container, false
        ).apply {
            appCompatActivity.setSupportActionBar(toolbar)
            lifecycleOwner = viewLifecycleOwner
            viewModel = workoutViewModel
            isCreationMode = this@WorkoutFragment.isCreationMode

            val adapter = WorkoutEntriesAdapter(requireContext(), this@WorkoutFragment, this@WorkoutFragment)
            entriesRecyclerView.adapter = adapter
            entriesRecyclerView.isNestedScrollingEnabled = true
            entriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            workoutViewModel.workoutAndEntries.observe(viewLifecycleOwner, Observer { workoutAndEntries ->
                println(" ON CHANGED WORKOUT_ENTRIES ${workoutAndEntries}");
                if (workoutAndEntries !== null) {
                    if(workoutAndEntries.entries.isNotEmpty()){
                        adapter.setEntriesAndSets(workoutAndEntries.entries)
                    }
                    var workoutName = workoutAndEntries.workout.name;
                    var workoutNameTextFieldValue: String =  binding.entryNameTextView.getText().toString();
                    println("are different ${workoutNameTextFieldValue !== workoutName} ?  ${workoutNameTextFieldValue} ${workoutName}")
                    if(!workoutAndEntries.workout.name.isEmpty() && workoutNameTextFieldValue !== workoutName){
                        println("Updating the name...");
                        binding.entryNameTextView.setText(workoutAndEntries.workout.name)
                    }
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
            ?.observe(viewLifecycleOwner, Observer { exerciseId ->
                Log.d(this.javaClass.canonicalName, "onViewCreated exerciseId ${exerciseId}. About to call onCreateWorkoutEntry");
                onCreateWorkoutEntry(exerciseId);
                navController.currentBackStackEntry?.savedStateHandle?.remove<Long>(SELECTED_EXERCISE)
            })

    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId().equals(R.id.action_save)) {
            binding.container.hideKeyboard();
            val name = binding.entryNameTextView.text.toString()
            workoutViewModel.createWorkout(name)
            Snackbar.make(binding.root, R.string.workout_created, Snackbar.LENGTH_LONG)
                .show()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.workout_menu, menu)
    }

    override fun onCreateSet(entryId: Long, weight: Double, reps: Short) {
        workoutViewModel.createSet(entryId, weight, reps)
    }

    override fun onEditSet(set: WSet, weight: Double, reps: Short) {
        workoutViewModel.editSet(set, weight, reps)
    }

    override fun onCreateWorkoutEntry(exerciseId: Long) {
        workoutViewModel.createEntry(exerciseId)
    }

    override fun onDeleteWorkoutEntry(entryId: Long) {
        workoutViewModel.deleteEntry(entryId)
    }

}
