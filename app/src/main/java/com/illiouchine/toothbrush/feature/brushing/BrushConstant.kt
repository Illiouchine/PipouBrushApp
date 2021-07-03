package com.illiouchine.toothbrush.feature.brushing

import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
val brushDuration = 3.toDuration(DurationUnit.MINUTES)
@ExperimentalTime
val countDownDuration = 3.toDuration(DurationUnit.SECONDS)
@ExperimentalTime
val tick = 1.toDuration(DurationUnit.SECONDS)
