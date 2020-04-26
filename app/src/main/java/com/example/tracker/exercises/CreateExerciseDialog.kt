package com.example.tracker.exercises

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.tracker.R
import com.example.tracker.databinding.CreateExerciseDialogBinding
import kotlinx.android.synthetic.main.create_exercise_dialog.*


class CreateExerciseDialog : DialogFragment() {
    internal lateinit var listener: ExerciseListListener

    companion object {
        const val TAG = "example_dialog"
        fun newInstance(listener: ExerciseListListener) = CreateExerciseDialog().apply {
            this.listener = listener;

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<CreateExerciseDialogBinding>(
            inflater,
            R.layout.create_exercise_dialog,
            container,
            false
        )
            .apply {
                cancelButton.setOnClickListener(View.OnClickListener {
                    dismiss()
                })

                addButton.setOnClickListener(View.OnClickListener {
                    dismiss()
                    listener.onCreateExercise(exerciseNameTextView.text.toString())
                })
            }
        dialog!!.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.Theme_Tracker_Dialog
        )
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.getWindow().setLayout(width, height)
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        toolbar!!.title = this.getString(R.string.new_exercise)
    }
}
