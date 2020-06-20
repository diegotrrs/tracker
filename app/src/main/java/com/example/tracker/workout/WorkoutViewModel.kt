package com.example.tracker.workout


import android.provider.Settings.System.DATE_FORMAT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.common.entities.WSet
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.util.*


class WorkoutViewModel(
    private val workoutRepository: WorkoutRepository,
    private val setsRepository: SetsRepository,
    private val entriesRepository: EntriesRepository,
    var workoutId: Long
) : ViewModel() {

    private val workoutIdLVD: MutableLiveData<Long> = MutableLiveData(workoutId)

    val workoutAndEntries = Transformations.switchMap(workoutIdLVD) { id ->
        workoutRepository.getWorkoutById(workoutId)
    }
    /* val workoutAndEntries = workoutIdLVD.switchMap { id ->
         liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
             emit(workoutRepository.getWorkoutById(workoutId).value)
         }
     }*/

    //var workoutAndEntries = workoutRepository.getWorkoutById(workoutId)


    /*val workoutAndEntries: LiveData<WorkoutAndEntries> = liveData {
        println("> Retrieving workoutentries ${workoutId}")
        val data = workoutRepository.getWorkoutById(workoutId).value
        if(data === null){
            //emit(WorkoutAndEntries(listOf()) )
        } else {
            emit(data )
        }

    }*/

    /* fun reloadWorkoutAndEntries(): LiveData<WorkoutAndEntries>{
         println("> WorkoutViewModel:: workoutId ${workoutId}")
         return workoutRepository.getWorkoutById(workoutId)
     }*/

    fun createWorkout(name: String) {
        viewModelScope.launch {
            println("WorkoutViewModel::createWorkout  old workout id> ${workoutId}")
            workoutId = workoutRepository.createWorkout(name)
            workoutIdLVD.postValue(workoutId)
            println("WorkoutViewModel::createWorkout  new workout id> ${workoutId}")
            //workoutAndEntries = reloadWorkoutAndEntries()
            //workoutAndEntries.postValue(reloadWorkoutAndEntries().value)
            //println("WorkoutViewModel::createWorkout result> x")

            //println(workoutAndEntries.value)
        }
    }

    fun deleteWorkout(workoutId: Long) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workoutId)
        }
    }

    fun getDefaultWorkoutName(): String {
        return DateTimeFormatter.ofPattern("EEEE d MMMM").withZone(ZoneId.systemDefault()).format(LocalDateTime.now())
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

