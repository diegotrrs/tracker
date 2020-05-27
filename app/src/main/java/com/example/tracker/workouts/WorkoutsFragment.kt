package com.example.tracker.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.common.InjectorUtils
import com.example.tracker.common.TrackerApp
import com.example.tracker.databinding.WorkoutsFragmentBinding
import kotlinx.android.synthetic.main.workouts_fragment.*


class WorkoutsFragment : Fragment() {
    private val workoutsViewModel: WorkoutsViewModel by viewModels {
        InjectorUtils.provideWorkoutsViewModelFactory(requireContext())
    }

    private inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var binding = DataBindingUtil.inflate<WorkoutsFragmentBinding>(
            inflater, R.layout.workouts_fragment, container, false
        ).apply {
            viewModel = workoutsViewModel
            lifecycleOwner = viewLifecycleOwner

            appCompatActivity.setSupportActionBar(toolbar)
            createNewWorkoutButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_workouts_to_workout)
            }

            var adapter = WorkoutsListAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            workoutsViewModel.getWorkoutsByUser(TrackerApp.userId).observe(this.lifecycleOwner!!, Observer { workouts ->
                if (workouts.isNotEmpty()) {
                    adapter.setWorkoutsAndEntries(workouts[0].workoutsAndEntries)
                }
            });

        }
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitle(getString(R.string.workouts))
        toolbar?.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    interface Callback {
        fun action()
    }
}