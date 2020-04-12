package com.example.tracker.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tracker.R
import com.example.tracker.databinding.SettingsFragmentBinding
import com.example.tracker.databinding.StatsFragmentBinding
import kotlinx.android.synthetic.main.stats_fragment.*

class StatsFragment : Fragment() {

    private lateinit var statsViewModel: StatsViewModel

    inline val Fragment.appCompatActivity: AppCompatActivity get() = (activity as AppCompatActivity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statsViewModel =
            ViewModelProviders.of(this).get(StatsViewModel::class.java)

        var binding = DataBindingUtil.inflate<StatsFragmentBinding>(
            inflater,
            R.layout.stats_fragment,
            container,
            false
        ).apply {
            appCompatActivity.setSupportActionBar(toolbar)
            statsViewModel.text.observe(viewLifecycleOwner, Observer {
                statsText.text = it
            })
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitle(getString(R.string.stats))
    }
}