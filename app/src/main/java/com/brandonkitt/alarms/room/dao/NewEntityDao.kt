package com.brandonkitt.alarms.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandonkitt.alarms.room.dbo.AlarmDbo

@Dao
interface NewEntityDao {

    @Query("SELECT * FROM newEntities WHERE id = :newen")
    fun getAlarm(newEntityId: String): LiveData<AlarmDbo>

    @Insert
    fun addNewEntityId(newEntityId: String)

    @Delete
    fun deleteNewEntityId(newEntityId: String)

    @Query("SELECT EXISTS(SELECT * FROM newEntities WHERE id = :newEntityId)")
    fun isNewEntity(newEntityId: String): Boolean
}