package com.brandonkitt.alarms.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brandonkitt.alarms.DummyEndpoint
import com.brandonkitt.alarms.entities.AlarmEntity
import com.brandonkitt.alarms.room.dao.AlarmDao
import com.brandonkitt.alarms.room.dao.NewEntityDao
import javax.inject.Inject

class DetailsRepository  @Inject constructor(
    private val alarmDao: AlarmDao,
    private val newEntityDao: NewEntityDao,
    private val dummyEndpoint: DummyEndpoint
) {
    fun getAlarm(id: String): LiveData<AlarmEntity> = MutableLiveData(dummyEndpoint.getAlarm(id))  // Transformations.map(alarmDao.getAlarms()){ AlarmMapper.toEntity(it) }
    fun isNewAlarm(id: String): Boolean = newEntityDao.isNewEntity(id)
    fun deleteAlarm(id: String) = alarmDao.deleteAlarm()
}