package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun AchievementContent(
    achievementState: AchievementState = AchievementState.Loaded(
        achievements = previewDataAchievementList()
    )
) {
    Column {
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
                    LazyColumn {
                        items(achievementState.achievements){ achievement ->
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
    achievement: AchievementState.Achievement = previewDataAchievement()
) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        ),
        headlineText = { Text(text = achievement.name) },
        supportingText = { Text(text = achievement.description) },
        leadingContent = {
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.ic_achievement_trophy),
                contentDescription = "",
                tint = if(achievement.earned){
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            )
        }
    )
}