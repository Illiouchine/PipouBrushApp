package com.illiouchine.toothbrush.database

import com.illiouchine.toothbrush.database.datasource.coundown.CountDownDataSource
import com.illiouchine.toothbrush.usecase.datagateway.CountDownDurationDataGateway
import javax.inject.Inject
import kotlin.time.Duration

class CountDownDurationDataMapper @Inject constructor(
    private val countDownDataSource: CountDownDataSource
) : CountDownDurationDataGateway {
    override suspend fun getCountDownDuration(): Duration {
        return countDownDataSource.getCountDownDuration()
    }

    override suspend fun setCountDownDuration(duration: Duration) {
        countDownDataSource.setCountDownDuration(duration = duration)
    }
}