package com.brandonkitt.alarms.room.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newEntities")
data class NewEntityDbo(
    @PrimaryKey @ColumnInfo(name = "id")
    val newEntityId: String
)
