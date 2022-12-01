package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun AchievementContent(
    achievementState: AchievementState = AchievementState.Loaded(
        achievements = achievementList
    )
) {
    Column() {
        Text(
            text = "Achievement",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            when(achievementState){
                AchievementState.Error -> {
                    Text(text = "Error")
                }
                is AchievementState.Loaded -> {
                    Column {
                        achievementState.achievements.forEach{ achievement ->
                            AchievementRow(achievement)
                        }
                    }
                }
                AchievementState.Loading -> {
                    Text(text = "Loading")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AchievementRow(
    achievement: AchievementState.Achievement = achievementList.first()
) {
    ListItem(
        headlineText = { Text(text = achievement.name) },
        supportingText = { Text(text = achievement.description) },
        leadingContent = {
            Icon(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = R.drawable.ic_achievement_trophy),
                contentDescription = ""
            )
        }
    )
}

private val achievementList = listOf(
    AchievementState.Achievement("My achievement 1", "My achievement Description 1", true),
    AchievementState.Achievement("My achievement 2", "My achievement Description 2, can be longer ?", true),
    AchievementState.Achievement("My achievement 3", "My achievement Description 3, yes, it can be longer than that ? Should we limit char number ?", true),
)