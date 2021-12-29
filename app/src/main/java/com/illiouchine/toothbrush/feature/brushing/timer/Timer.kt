package com.illiouchine.toothbrush.feature.brushing.timer

private const val DefaultDurationInMillisecond: Long = 3 * 1000L
private const val InitialDelay: Long = 0 * 1000L

class Timer(
    val duration: Long = DefaultDurationInMillisecond,
) {
    private var startingPause: Long = 0L
    private var pauseDuration: Long = 0L

    private var startTime: Long = 0L // Todo we can avoid this
    private var endTimer: Long = 0L

    fun getRemindingTime(): Long {
        return if (isPaused()) {
            (endTimer + (System.currentTimeMillis() - startingPause)) - System.currentTimeMillis()
        } else {
            (endTimer + pauseDuration) - System.currentTimeMillis()
        }
    }

    private fun isPaused(): Boolean = startingPause != 0L

    fun start(
        initialDelay: Long = InitialDelay
    ) {
        val now = System.currentTimeMillis()
        startTime = now + initialDelay
        endTimer = startTime + duration
    }

    fun pause() {
        if (isPaused()) {
            throw TimerException("Timer already paused")
        } else {
            startingPause = System.currentTimeMillis()
        }
    }

    fun resume() {
        if (isPaused()) {
            pauseDuration += System.currentTimeMillis() - startingPause
            startingPause = 0L
        } else {
            throw TimerException("Timer already resumed")
        }
    }

    class TimerException(msg: String) : Exception(msg)
}
