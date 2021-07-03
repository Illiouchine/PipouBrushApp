package com.illiouchine.toothbrush.feature.brushing.usecase

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LaunchVibratorUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    operator fun invoke() {
        val vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Todo : Make a vibration pattern --.'.'''
            vibrator.vibrate(VibrationEffect.createOneShot(1000, 100))
            vibrator.vibrate(VibrationEffect.createOneShot(1000, 100))
        } else {
            vibrator.vibrate(1000)
        }
    }
}