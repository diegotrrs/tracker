package com.example.tracker.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.WorkoutsFragmentBinding
import kotlinx.android.synthetic.main.workouts_fragment.*


//import com.example.tracker.home.HomeFragmentDirections
//import com.example.tracker.home.workouts

class WorkoutsFragment : Fragment() {

    private lateinit var workoutsViewModel: WorkoutsViewModel

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

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
                appCompatActivity.setSupportActionBar(toolbar)
                this.createNewWorkoutButton.setOnClickListener {
                    it.findNavController().navigate(R.id.action_workouts_to_newworkout)
                }

                viewModel = workoutsViewModel
                lifecycleOwner = viewLifecycleOwner


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