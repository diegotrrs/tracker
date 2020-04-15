package com.example.tracker.exercises

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.ExercisesFragmentBinding
import kotlinx.android.synthetic.main.exercises_fragment.*


class ExercisesFragment : Fragment() {

    private lateinit var exercisesViewModel: ExercisesViewModel

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    companion object {
        private const val CREATE_EXERCISE_REQUEST_CODE = 0
    }
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

    private fun displayCreateExerciseDialog() {
        //CreateExerciseDialog.display(appCompatActivity.supportFragmentManager);
        val exampleDialog = CreateExerciseDialog()
        exampleDialog.show(this.requireFragmentManager(), CreateExerciseDialog.TAG)
        exampleDialog.setTargetFragment( this, CREATE_EXERCISE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("ON ACTIVITY RESULT " +  requestCode + resultCode + data)
        println(data!!.getStringExtra("ff"))
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() === R.id.action_add) {
            displayCreateExerciseDialog();

        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercises_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitle(getString(R.string.exercises))
    }

    interface Callback {
        fun action()
    }
}