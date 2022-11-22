package com.illiouchine.toothbrush.feature

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.feature.home.MainScreen
import com.illiouchine.toothbrush.feature.settings.SettingsViewModel
import com.illiouchine.toothbrush.feature.statistics.StatisticsViewModel
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    private val brushViewModel by viewModels<BrushViewModel>()
    private val statisticsViewModel by viewModels<StatisticsViewModel>()
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToothBrushTheme {
                MainScreen(
                    brushViewModel = brushViewModel,
                    statisticsViewModel = statisticsViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}