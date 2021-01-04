package com.illiouchine.toothbrush.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.illiouchine.toothbrush.feature.brushing.*
import com.illiouchine.toothbrush.feature.brushing.ui.BrushScreen
import com.illiouchine.toothbrush.feature.brushing.ui.composable.video.VideoExoPlayer
import com.illiouchine.toothbrush.feature.calendar.composable.CalendarScreen
import com.illiouchine.toothbrush.feature.main.composable.BottomNavigationBar
import kotlin.time.ExperimentalTime


@ExperimentalTime
@Composable
fun MainScreen(
    brushViewModel: BrushViewModel
) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Brush,
        Screen.Calendar,
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
                composable(Screen.Calendar.route) {
                    CalendarScreen()
                }
                composable(Screen.Settings.route) {
                    VideoExoPlayer()
                }
            }
        }
    }
}
