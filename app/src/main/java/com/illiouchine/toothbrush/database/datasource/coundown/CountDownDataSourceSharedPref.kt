package com.illiouchine.toothbrush.database.datasource.coundown

import android.content.Context
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class CountDownDataSourceSharedPref @Inject constructor(
    private val context: Context
) : CountDownDataSource {

    private val preferencesFileKey = "CountDownPreferencesFileKey"
    private val durationKey = "CountDownDurationKey"

    override suspend fun getCountDownDuration(): Duration {
        return context.getSharedPreferences(preferencesFileKey, Context.MODE_PRIVATE)
            .getLong(durationKey, 180L)
            .toDuration(DurationUnit.SECONDS)
    }

    override suspend fun setCountDownDuration(duration: Duration) {
        context.getSharedPreferences(preferencesFileKey, Context.MODE_PRIVATE)
            .edit()
            .apply {
                putLong(durationKey, duration.toLong(DurationUnit.SECONDS))
                apply()
            }
    }
}