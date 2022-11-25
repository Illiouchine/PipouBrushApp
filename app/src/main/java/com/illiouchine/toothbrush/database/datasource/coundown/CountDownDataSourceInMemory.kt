package com.illiouchine.toothbrush.database.datasource.coundown

import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class CountDownDataSourceInMemory @Inject constructor() : CountDownDataSource {

    private var countDownDuration: Duration = 180.seconds

    override suspend fun getCountDownDuration(): Duration {
        return countDownDuration
    }

    override suspend fun setCountDownDuration(duration: Duration) {
        countDownDuration = duration
    }
}