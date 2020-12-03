package com.brandonkitt.alarms.viewmodel

import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.brandonkitt.alarms.entities.AlarmEntity
import com.brandonkitt.alarms.repository.DetailsRepository
import kotlinx.coroutines.launch
import java.util.*

class DetailsViewModel @ViewModelInject constructor(
    private val repository: DetailsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val mediatorAlarm: MediatorLiveData<AlarmEntity> = MediatorLiveData<AlarmEntity>()
    val alarm: LiveData<AlarmEntity> = mediatorAlarm

    private val id: String
        get() = savedStateHandle.get<String>("id") ?: ""

    init { mediatorAlarm.addSource(repository.getAlarm(id), mediatorAlarm::setValue) }

    private val _action: MutableLiveData<Action> = MutableLiveData()
    val action: LiveData<Action> = _action

    val afterTextChanged = TextViewBindingAdapter.AfterTextChanged {

    }

    fun onCancel(){
        viewModelScope.launch {
            if (repository.isNewAlarm(id)){
                repository.deleteAlarm(id)
            }
        }
        _action.value = Action.Dismiss
    }

    fun onSave(){
        repository.saveAlarm()
    }


    sealed class Action {
        object Dismiss: Action()
    }
}