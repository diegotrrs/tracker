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


class SetDialog : DialogFragment() {
    internal lateinit var listener: SetsListener
    internal lateinit var mode: Mode
    internal lateinit var set: WSet
    internal var entryId: Long = -1

    private inline val isCreationMode: Boolean get() = (mode == Mode.CREATION)

    companion object {
        enum class Mode {
            CREATION, EDITION
        }

        const val TAG = "set_dialog"

        fun createDialogForEdition(set: WSet?, listener: SetsListener) = SetDialog().apply {
            this.listener = listener;
            this.set = set!!
            this.mode = Mode.EDITION
        }

        fun createDialogForCreation(entryId: Long, listener: SetsListener) = SetDialog().apply {
            this.listener = listener;
            this.entryId = entryId
            this.mode = Mode.CREATION
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

            actionButton.setOnClickListener(View.OnClickListener {
                dismiss()
                var weight = weightTextView.text.toString().toDouble();
                var reps = repsTextView.text.toString().toShort();
                if (isCreationMode) {
                    listener.onCreateSet(entryId, weight, reps)
                } else {
                    listener.onEditSet(set, weight, reps)
                }

            })
        }
        binding.isCreationMode = isCreationMode;
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
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.getWindow().setLayout(width, height)
        }
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (isCreationMode) {
            toolbar!!.title = this.getString(R.string.new_set)
        } else {
            toolbar!!.title = this.getString(R.string.edit_set)
        }
    }
}
