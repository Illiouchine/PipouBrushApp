package com.illiouchine.toothbrush.database.datasource.brushhistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject

@Dao
interface BrushHistoryDataSource {
    @Query("SELECT * FROM brush_history")
    suspend fun getBrushHistory(): List<BrushHistoryObject>
    @Insert
    suspend fun addBrushFinished(date: BrushHistoryObject)
}