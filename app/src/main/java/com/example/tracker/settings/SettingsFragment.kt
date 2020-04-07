package com.example.tracker.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.tracker.R
import kotlinx.android.synthetic.main.basic_toolbar.*

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.settings_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var toolbar = inflater.inflate(R.layout.basic_toolbar, container, false) as Toolbar?;
        appCompatActivity.setSupportActionBar(toolbar)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbarTitle.setText(getString(R.string.title_settings))
    }

}