package com.illiouchine.toothbrush.database.datasource.coundown

import kotlin.time.Duration

interface CountDownDataSource {
    suspend fun getCountDownDuration(): Duration
    suspend fun setCountDownDuration(duration: Duration)
}