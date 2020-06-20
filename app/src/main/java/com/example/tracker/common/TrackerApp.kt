package com.example.tracker.common

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen


class TrackerApp: Application(){

    companion object {
        var userId: Long = 1
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}