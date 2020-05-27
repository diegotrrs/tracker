package com.example.tracker.workout


import androidx.lifecycle.*
import com.example.tracker.common.TrackerApp
import com.example.tracker.common.entities.WSet
import com.example.tracker.common.entities.WorkoutAndEntries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val workoutRepository: WorkoutRepository,
    private val setsRepository: SetsRepository,
    private val entriesRepository: EntriesRepository,
    var workoutId: Long
) : ViewModel() {

    private val workoutIdLVD: MutableLiveData<Long> = MutableLiveData(workoutId)

   /* val workoutAndEntries = workoutIdLVD.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(workoutRepository.getWorkoutById(workoutId))
        }
    }*/

    var workoutAndEntries = workoutRepository.getWorkoutById(workoutId)


    /*val workoutAndEntries: LiveData<WorkoutAndEntries> = liveData {
        println("> Retrieving workoutentries ${workoutId}")
        val data = workoutRepository.getWorkoutById(workoutId).value
        if(data === null){
            //emit(WorkoutAndEntries(listOf()) )
        } else {
            emit(data )
        }

    }*/

    fun reloadWorkoutAndEntries(): LiveData<WorkoutAndEntries>{
        println("> WorkoutViewModel:: workoutId ${workoutId}")
        return workoutRepository.getWorkoutById(workoutId)
    }

    fun createWorkout(name: String) {
        viewModelScope.launch {
            workoutId = workoutRepository.createWorkout(name)
            workoutIdLVD.postValue(workoutId)
            println("WorkoutViewModel::createWorkout > ${workoutId}")
            //workoutAndEntries = reloadWorkoutAndEntries()
            //workoutAndEntries.postValue(reloadWorkoutAndEntries().value)
            println("WorkoutViewModel::createWorkout result> x")

            println(workoutAndEntries.value)
        }
    }

    fun createSet(entryId: Long, weight: Double, reps: Short) {
        viewModelScope.launch {
            setsRepository.createSet(entryId, weight, reps)
        }
    }

    fun editSet(set: WSet, weight: Double, reps: Short) {
        viewModelScope.launch {
            set.reps = reps
            set.weight = weight
            setsRepository.updateSet(set)
        }
    }

    fun createEntry(exerciseId: Long) {
        viewModelScope.launch {
            entriesRepository.createEntry(workoutId, exerciseId)
        }
    }

    fun deleteEntry(entryId: Long) {
        viewModelScope.launch {
            entriesRepository.deleteEntry(entryId)
        }
    }
}

