package com.brandonkitt.alarms.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.brandonkitt.alarms.entities.AlarmEntity
import com.brandonkitt.alarms.repository.ListingRepository
import com.brandonkitt.alarms.room.dbo.AlarmDbo

class ListingViewModel @ViewModelInject constructor(
    repository: ListingRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    val alarms: LiveData<List<AlarmEntity>> = repository.getAlarms()

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    fun onAddAlarm(){
        _action.value = Action.AddAlarm
    }

    sealed class Action {
        object AddAlarm: Action()
        data class ViewAlarmDetails(val alarmId: String): Action()
    }



}