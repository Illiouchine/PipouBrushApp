package com.illiouchine.toothbrush.database.dataobject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "achievement")
data class AchievementObject(
    @PrimaryKey val code: Int,
    val unlockDate: Date
)
