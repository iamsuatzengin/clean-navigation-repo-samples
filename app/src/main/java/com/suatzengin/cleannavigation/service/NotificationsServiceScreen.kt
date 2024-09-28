package com.suatzengin.cleannavigation.service

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun NotificationsServiceScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Notifications Service Screen")
        Button(
            onClick = {
                context.showPermissionSettings()
            }
        ) {
            Text(text = "App access settings")
        }
    }
}

fun Context.showPermissionSettings() {
    val intent = Intent(
        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(intent)
}