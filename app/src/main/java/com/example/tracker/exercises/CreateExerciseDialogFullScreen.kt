package com.example.tracker.exercises

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.tracker.R


class CreateExerciseDialogFullScreen : DialogFragment() {
    private var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.Theme_Tracker_FullScreenDialog
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.create_exercise_full_screen_dialog, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        toolbar!!.setNavigationOnClickListener { v: View? -> dismiss() }
        toolbar!!.title = this.getString(R.string.new_exercise)
        //toolbar!!.inflateMenu(R.menu.new_exercise_menu)
        toolbar!!.setOnMenuItemClickListener { item: MenuItem? ->
            dismiss()
            true
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.getWindow().setLayout(width, height)
            dialog.getWindow().setWindowAnimations(R.style.Theme_Tracker_Slide);
        }
    }

    companion object {
        const val TAG = "example_dialog"
        fun display(fragmentManager: FragmentManager?): CreateExerciseDialogFullScreen {
            val exampleDialog = CreateExerciseDialogFullScreen()
            exampleDialog.show(fragmentManager!!, TAG)
            return exampleDialog
        }
    }
}