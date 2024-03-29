package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun AchievementContent(
    modifier: Modifier = Modifier,
    achievementState: AchievementState = AchievementState.Loaded(
        achievements = previewDataAchievementList()
    )
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.semantics { heading() },
            text = stringResource(R.string.achievement_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            when (achievementState) {
                AchievementState.Error -> {
                    Text(text = stringResource(R.string.achievement_error_description))
                }
                is AchievementState.Loaded -> {
                    LazyColumn {
                        items(achievementState.achievements) { achievement ->
                            AchievementRow(achievement)
                        }
                    }
                }
                AchievementState.Loading -> {
                    Text(text = stringResource(R.string.achievement_loading_description))
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
        modifier = Modifier.semantics(mergeDescendants = true) {},
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent
        ),
        headlineText = { Text(text = achievement.name) },
        supportingText = { Text(text = achievement.description) },
        leadingContent = {
            Icon(
                modifier = Modifier
                    .size(44.dp)
                    .background(MaterialTheme.colorScheme.onSecondary, shape = CircleShape)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_achievement_trophy),
                contentDescription = if (achievement.earned) {
                    stringResource(R.string.achievement_content_achievement_earned_icon_accessibility_label)
                } else {
                    stringResource(R.string.achievement_content_achievement_locked_icon_accessibility_label)
                },
                tint = if (achievement.earned) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            )
        }
    )
}