package com.example.tracker.newworkout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tracker.R
import com.example.tracker.databinding.NewWorkoutFragmentBinding


class NewWorkoutFragment : Fragment() {

    companion object {
        fun newInstance() = NewWorkoutFragment()
    }

    private lateinit var viewModel: NewWorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var binding = DataBindingUtil.inflate<NewWorkoutFragmentBinding>(inflater, R.layout.new_workout_fragment, container, false).apply {

            this.addExerciseButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_newworkout_to_exercises)
            }
        };

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewWorkoutViewModel::class.java)
    }

    interface Callback {
        fun action()
    }


}
