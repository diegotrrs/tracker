package com.example.tracker.common

import android.app.Application
import com.example.tracker.common.entities.User

class TrackerApp: Application(){

    companion object {
        var userId: Long = 1
    }

}