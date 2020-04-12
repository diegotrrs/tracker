package com.example.tracker.settings

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tracker.R
import com.example.tracker.databinding.SettingsFragmentBinding
import kotlinx.android.synthetic.main.settings_fragment.*

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

        var binding = DataBindingUtil.inflate<SettingsFragmentBinding>(
            inflater,
            R.layout.settings_fragment,
            container,
            false
        ).apply {
            appCompatActivity.setSupportActionBar(toolbar)
            settingsViewModel.text.observe(viewLifecycleOwner, Observer {
                textTest.text = it
            })
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitle(getString(R.string.settings))
    }
}