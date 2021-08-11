package com.illiouchine.toothbrush.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import com.illiouchine.toothbrush.feature.brushing.controller.BrushViewModel
import com.illiouchine.toothbrush.feature.statistics.controller.StatisticsViewModel
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.ExperimentalTime

@ExperimentalTime
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val brushViewModel by viewModels<BrushViewModel>()
    private val statisticsViewModel by viewModels<StatisticsViewModel>()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToothBrushTheme {
                MainScreen(
                    brushViewModel = brushViewModel,
                    statisticsViewModel = statisticsViewModel
                )
            }
        }
    }
}