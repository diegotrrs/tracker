package com.example.tracker.exercises

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.ExercisesFragmentBinding
import kotlinx.android.synthetic.main.exercises_fragment.*


class ExercisesFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exercisesViewModel = ViewModelProviders.of(this).get(ExercisesViewModel::class.java)
        //var toolbar = inflater.inflate(R.layout.exercises_toolbar, container, false) as Toolbar?;
        //appCompatActivity.setSupportActionBar(toolbar)
        //appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        var binding = DataBindingUtil.inflate<ExercisesFragmentBinding>(
            inflater,
            R.layout.exercises_fragment,
            container,
            false
        )
            .apply {

                appCompatActivity.setSupportActionBar(toolbar)
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        println("CLOCK")
        if (menuItem.getItemId() === android.R.id.home) {

        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercises_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.setNavigationOnClickListener { view ->
            println("UP! on  activity created")
            view.findNavController().navigateUp()
        }

        toolbar.setTitle(getString(R.string.exercises))
    }

    interface Callback {
        fun action()
    }
}