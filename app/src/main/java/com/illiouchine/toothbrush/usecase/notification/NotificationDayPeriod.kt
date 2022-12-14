package com.illiouchine.toothbrush.usecase.notification

enum class NotificationDayPeriod(
    val notificationId: Int,
    val intentAction: String
) {
    Morning(100, "intent_action_morning"),
    Midday(200, "intent_action_midday"),
    Evening(300, "intent_action_evening"),
}