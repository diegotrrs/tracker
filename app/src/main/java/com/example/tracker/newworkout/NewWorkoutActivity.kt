package com.example.tracker.newworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tracker.R
import com.example.tracker.newworkout.ui.newworkout.NewWorkoutFragment

class NewWorkoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_workout_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewWorkoutFragment.newInstance())
                .commitNow()
        }
    }

}
