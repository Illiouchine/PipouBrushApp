package com.illiouchine.toothbrush.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.illiouchine.toothbrush.feature.Screen
import com.illiouchine.toothbrush.feature.brushing.BrushContract
import com.illiouchine.toothbrush.feature.brushing.BrushScreen
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.feature.screenList
import com.illiouchine.toothbrush.feature.settings.SettingsContract
import com.illiouchine.toothbrush.feature.settings.SettingsScreen
import com.illiouchine.toothbrush.feature.settings.SettingsViewModel
import com.illiouchine.toothbrush.ui.composable.BottomNavigationBar
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract
import com.illiouchine.toothbrush.feature.statistics.StatisticsScreen
import com.illiouchine.toothbrush.feature.statistics.StatisticsViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = screenList
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Brush.route
            ) {
                composable(Screen.Brush.route) {
                    val brushViewModel = hiltViewModel<BrushViewModel>()
                    val brushState by brushViewModel.uiState.collectAsState()
                    BrushScreen(
                        timerState = brushState.timer,
                        onRestartClick = { brushViewModel.dispatchIntent(BrushContract.BrushIntent.ResetBrushing) },
                        onStartClick = { brushViewModel.dispatchIntent(BrushContract.BrushIntent.StartBrushing) }
                    )
                }
                composable(Screen.Statistics.route) {
                    val statisticsViewModel = hiltViewModel<StatisticsViewModel>()
                    // TODO make the statisticsScreen VM agnostic like in brush screen
                    statisticsViewModel.dispatchIntent(StatisticsContract.StatisticsIntent.LoadScreen)
                    StatisticsScreen(
                        viewModel = statisticsViewModel
                    )
                }
                composable(Screen.Settings.route) {
                    val settingsViewModel = hiltViewModel<SettingsViewModel>()
                    // TODO make the statisticsScreen VM agnostic like in brush screen
                    settingsViewModel.dispatchIntent(SettingsContract.SettingsIntent.LoadScreen)
                    SettingsScreen(
                        viewModel = settingsViewModel
                    )
                }
            }
        }
    }
}
