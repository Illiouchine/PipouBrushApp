package com.illiouchine.toothbrush.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.illiouchine.toothbrush.feature.Screen
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.feature.brushing.BrushScreen
import com.illiouchine.toothbrush.feature.main.composable.BottomNavigationBar
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract
import com.illiouchine.toothbrush.feature.statistics.StatisticsViewModel
import com.illiouchine.toothbrush.feature.statistics.ui.StatisticsScreen
import com.illiouchine.toothbrush.ui.composable.video.VideoExoPlayer
import kotlin.time.ExperimentalTime


@ExperimentalMaterialApi
@ExperimentalTime
@Composable
fun MainScreen(
    brushViewModel: BrushViewModel,
    statisticsViewModel: StatisticsViewModel
) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Brush,
        Screen.Statistics,
        Screen.Settings
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = items
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
                    BrushScreen(
                        viewModel = brushViewModel
                    )
                }
                composable(Screen.Statistics.route) {
                    statisticsViewModel.dispatchIntent(StatisticsContract.StatisticsIntent.LoadScreen)
                    StatisticsScreen(
                        viewModel = statisticsViewModel
                    )
                }
                composable(Screen.Settings.route) {
                    VideoExoPlayer()
                }
            }
        }
    }
}
