package com.example.tracker.newworkout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tracker.R
import com.example.tracker.databinding.NewWorkoutFragmentBinding
import kotlinx.android.synthetic.main.basic_toolbar.*


class NewWorkoutFragment : Fragment() {

    companion object {
        fun newInstance() = NewWorkoutFragment()
    }

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    private lateinit var viewModel: NewWorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProviders.of(this).get(NewWorkoutViewModel::class.java)
        var binding = DataBindingUtil.inflate<NewWorkoutFragmentBinding>(inflater, R.layout.new_workout_fragment, container, false).apply {

            this.addExerciseButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_newworkout_to_exercises)
            }
        };

        var toolbar = inflater.inflate(R.layout.basic_toolbar, container, false) as Toolbar?;
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbarTitle.setText(getString(R.string.title_new_workout))
        toolbar?.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }


    interface Callback {
        fun action()
    }


}
