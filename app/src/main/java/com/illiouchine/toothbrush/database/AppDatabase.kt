package com.illiouchine.toothbrush.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.illiouchine.toothbrush.database.dataobject.BrushHistoryObject
import com.illiouchine.toothbrush.database.datasource.brushhistory.BrushHistoryDataSource
import com.illiouchine.toothbrush.database.roomTypeConverter.DateConverter

@Database(entities = [BrushHistoryObject::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun brushHistoryDao():BrushHistoryDataSource
}