package com.illiouchine.toothbrush.usecase.datagateway

import kotlin.time.Duration

interface CountDownDurationDataGateway {
    suspend fun getCountDownDuration() : Duration
    suspend fun setCountDownDuration(duration: Duration)
}