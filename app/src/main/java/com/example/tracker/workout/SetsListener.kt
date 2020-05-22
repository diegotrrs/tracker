package com.example.tracker.workout

import com.example.tracker.common.entities.WSet

interface SetsListener {
    fun onCreateSet(entryId: Long, weight: Double, reps: Short);
    fun onEditSet(set: WSet, weight: Double, reps: Short);
}