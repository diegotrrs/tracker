package com.example.tracker.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tracker.R
import com.example.tracker.databinding.TrackerActivityBinding

class TrackerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<TrackerActivityBinding>(this, R.layout.tracker_activity)
    }
}