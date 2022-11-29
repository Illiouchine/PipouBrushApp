package com.illiouchine.toothbrush.database.dataobject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "brush_history")
data class BrushHistoryObject(
    @PrimaryKey val date: Date
)