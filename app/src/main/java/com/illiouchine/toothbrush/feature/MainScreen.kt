package com.illiouchine.toothbrush.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.illiouchine.toothbrush.feature.achievements.AchievementsContract
import com.illiouchine.toothbrush.feature.achievements.AchievementsScreen
import com.illiouchine.toothbrush.feature.achievements.AchievementsViewModel
import com.illiouchine.toothbrush.feature.brushing.BrushContract
import com.illiouchine.toothbrush.feature.brushing.BrushScreen
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.feature.settings.SettingsContract
import com.illiouchine.toothbrush.feature.settings.SettingsScreen
import com.illiouchine.toothbrush.feature.settings.SettingsViewModel
import com.illiouchine.toothbrush.feature.statistics.StatisticsContract
import com.illiouchine.toothbrush.feature.statistics.StatisticsScreen
import com.illiouchine.toothbrush.feature.statistics.StatisticsViewModel
import com.illiouchine.toothbrush.ui.composable.BottomNavigationBar
import com.illiouchine.toothbrush.ui.composable.settings.reminder.ReminderDayPeriod
import kotlin.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
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
                        achievements = brushState.achievement,
                        onRestartClick = { brushViewModel.dispatchIntent(BrushContract.BrushIntent.ResetBrushing) },
                        onStartClick = { brushViewModel.dispatchIntent(BrushContract.BrushIntent.StartBrushing) },
                        onAchievementsHandled = { brushViewModel.dispatchIntent(BrushContract.BrushIntent.AchievementHandled) }
                    )
                }
                composable(Screen.Statistics.route) {
                    val statisticsViewModel = hiltViewModel<StatisticsViewModel>()
                    val statisticsState by statisticsViewModel.uiState.collectAsState()
                    statisticsViewModel.dispatchIntent(StatisticsContract.StatisticsIntent.LoadScreen)
                    StatisticsScreen(
                        statisticsState = statisticsState.statisticsState,
                    )
                }
                composable(Screen.Achievement.route) {
                    val achievementsViewModel = hiltViewModel<AchievementsViewModel>()
                    val achievementsState by achievementsViewModel.uiState.collectAsState()
                    achievementsViewModel.dispatchIntent(AchievementsContract.AchievementsIntent.LoadScreen)
                    AchievementsScreen(
                        achievementState = achievementsState.achievementState
                    )
                }
                composable(Screen.Settings.route) {
                    val settingsViewModel = hiltViewModel<SettingsViewModel>()
                    val settingsState by settingsViewModel.uiState.collectAsState()
                    SettingsScreen(
                        countDownSettings = settingsState.countDownSettings,
                        event = settingsState.event,
                        onCountDownDurationChanged = { duration: Duration ->
                            settingsViewModel.dispatchIntent(
                                SettingsContract.SettingsIntent.UpdateCountDownDuration(duration)
                            )
                        },
                        onEventHandled = { settingsEvent ->
                            settingsViewModel.dispatchIntent(
                                SettingsContract.SettingsIntent.EventHandled(settingsEvent)
                            )
                        },
                        onNotificationCheckedChanged = { checked, reminderType, hour, min ->
                            settingsViewModel.dispatchIntent(
                                SettingsContract.SettingsIntent.ReminderChanged(
                                    checked, reminderType.toVMData(), hour, min
                                )
                            )
                        },
                        morningReminder = settingsState.morningReminderState,
                        middayReminder = settingsState.middayReminderState,
                        eveningReminder = settingsState.eveningReminderState,
                        onThanksClicked = {
                            settingsViewModel.dispatchIntent(
                                SettingsContract.SettingsIntent.ThanksClicked
                            )
                        }
                    )
                }
            }
        }
    }
}

private fun ReminderDayPeriod.toVMData(): SettingsContract.ReminderDayPeriod {
    return when (this) {
        ReminderDayPeriod.Evening -> SettingsContract.ReminderDayPeriod.Evening
        ReminderDayPeriod.Midday -> SettingsContract.ReminderDayPeriod.Midday
        ReminderDayPeriod.Morning -> SettingsContract.ReminderDayPeriod.Morning
    }
}
