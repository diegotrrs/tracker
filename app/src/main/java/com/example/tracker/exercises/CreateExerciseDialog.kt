package com.example.tracker.exercises

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.tracker.R
import com.example.tracker.databinding.CreateExerciseDialogBinding

import kotlinx.android.synthetic.main.create_exercise_dialog.*

class CreateExerciseDialog : DialogFragment() {

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

            }

        binding.callback = Callback(this)
        return binding.root
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

    class Callback constructor(private val dialog: DialogFragment) {
         fun close() {
            dialog.dismiss()
        }
        fun add() {
            println("dialog")
            println(dialog)
            dialog.getTargetFragment()!!.onActivityResult(2, 2, Intent().apply { putExtra("ff", "aaaaa") } )
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        toolbar!!.title = this.getString(R.string.new_exercise)
    }

    companion object {
        const val TAG = "example_dialog"
        fun display(fragmentManager: FragmentManager?): CreateExerciseDialog {
            val exampleDialog = CreateExerciseDialog()
            exampleDialog.show(fragmentManager!!, TAG)
            return exampleDialog
        }
    }
}