package com.illiouchine.toothbrush.feature.brushing

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
val brushDuration: Duration = 3.toDuration(DurationUnit.SECONDS)
@ExperimentalTime
val tick: Duration = 1.toDuration(DurationUnit.SECONDS)
