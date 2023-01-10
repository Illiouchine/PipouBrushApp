package com.illiouchine.toothbrush.database.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationFrom1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `achievement` (`code` INTEGER, `unlockDate` LONG, " +
                    "PRIMARY KEY(`code`))"
        )

    }
}