package com.brandonkitt.alarms.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.brandonkitt.alarms.entities.AlarmEntity
import com.brandonkitt.alarms.repository.ListingRepository
import com.brandonkitt.alarms.room.dbo.AlarmDbo
import kotlinx.coroutines.launch
import java.util.*

class ListingViewModel @ViewModelInject constructor(
    private val repository: ListingRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    val alarms: LiveData<List<AlarmEntity>> = repository.getAlarms()

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    fun onAddAlarm(){
        viewModelScope.launch {
            val alarm = createNewAlarm()
            repository.insertAlarm(alarm)
            _action.value = Action.AddAlarm
        }
    }

    fun onAlarmSelected(id: String) {
        _action.value = Action.ViewAlarmDetails(alarmId = id)
    }

    private fun createNewAlarm(): AlarmEntity {
        return AlarmEntity(
            alarmId = UUID.randomUUID().toString(),
            description = "",
            time = Date(),
            days = emptyList(),
            enabled = false
        )
    }

    sealed class Action {
        object AddAlarm: Action()
        data class ViewAlarmDetails(val alarmId: String): Action()
    }



}