package com.illiouchine.toothbrush

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration


@ExperimentalTime
private val initialDuration = 3.toDuration(DurationUnit.MINUTES)
@ExperimentalTime
val tick = 1.toDuration(DurationUnit.SECONDS)
@ExperimentalTime
val initialDelay = 4.toDuration(DurationUnit.SECONDS)

@ExperimentalTime
class MainActivityViewModel : ViewModel() {

    private val _currentDuration = MutableLiveData<Double>()
    val currentDuration: LiveData<Double> = _currentDuration

    fun launchTimer() {
        viewModelScope.launch {
            startTimer(initialDuration = initialDuration)
                .collect {
                    _currentDuration.value = it
                }

        }
    }
}

@ExperimentalTime
private fun startTimer(initialDuration: Duration): Flow<Double> = flow {
    var currentDuration: Duration = initialDuration
    delay(initialDelay)
    while (currentDuration.inSeconds >= 0) {
        emit(currentDuration.toDouble(DurationUnit.SECONDS))
        currentDuration = currentDuration.minus(tick)
        delay(tick)
    }
}
