package com.example.tracker.workout

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.tracker.R
import com.example.tracker.common.entities.WSet
import com.example.tracker.databinding.SetDialogBinding
import kotlinx.android.synthetic.main.create_exercise_dialog.toolbar


class CreateSetDialog : DialogFragment() {
    internal lateinit var listener: SetsListener
    internal var entryId: Long = -1

    companion object {
        const val TAG = "create_dialog"
        fun getInstance(entryId: Long, listener: SetsListener) = CreateSetDialog().apply {
            this.listener = listener;
            this.entryId = entryId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<SetDialogBinding>(
            inflater, R.layout.set_dialog, container, false
        ).apply {
            cancelButton.setOnClickListener(View.OnClickListener {
                dismiss()
            })

            title = getString(R.string.new_set)

            actionButton.setOnClickListener(View.OnClickListener {
                dismiss()
                var weight = weightTextView.text.toString().toDouble();
                var reps = repsTextView.text.toString().toShort();

                listener.onCreateSet(entryId, weight, reps)
            })
        }
        dialog!!.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL, R.style.Theme_Tracker_Dialog
        )
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
