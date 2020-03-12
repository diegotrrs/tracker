package com.example.tracker.newworkout

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tracker.R


class NewWorkoutFragment : Fragment() {

    companion object {
        fun newInstance() = NewWorkoutFragment()
    }

    private lateinit var viewModel: NewWorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.new_workout_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewWorkoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
