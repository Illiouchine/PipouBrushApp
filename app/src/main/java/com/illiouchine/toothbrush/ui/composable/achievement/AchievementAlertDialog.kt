package com.illiouchine.toothbrush.ui.composable.achievement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

data class AlertDialogAchievement(
    val nameResId: Int,
    val descriptionResId: Int
)

@Composable
fun AchievementAlertDialog(
    onAchievementsHandled: ()-> Unit = {},
    achievements: List<AlertDialogAchievement> = emptyList(),
) {
    AlertDialog(
    onDismissRequest = {
        onAchievementsHandled()
    },
    icon = {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.ic_achievement_trophy),
            contentDescription = null
        )
    },
    title = {
        Text(text = stringResource(R.string.brush_achievement_dialog_title))
    },
    text = {
        LazyColumn {
            items(achievements) { achievement ->
                Row {
                    Text(text = stringResource(id = achievement.nameResId))
                }
            }
        }
    },
    confirmButton = {
        TextButton(
            onClick = {
                onAchievementsHandled()
            }
        ) {
            Text(text = stringResource(R.string.brush_achievement_dialog_confirm_button))
        }
    },
    dismissButton = {}
    )
}