package com.suatzengin.cleannavigation.screen

import android.content.Context
import android.os.Parcelable
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suatzengin.cleannavigation.currentNavigator
import com.suatzengin.cleannavigation.navigation.Screen
import com.suatzengin.cleannavigation.navigation.SettingResultModel
import com.suatzengin.cleannavigation.navigator.Data
import kotlinx.parcelize.Parcelize

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val deviceId = remember {
        context.myDeviceId
    }
    val navigator = currentNavigator

    val settingsResult = remember {
        navigator.restoreData<SettingResultModel>("result")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Magenta.copy(alpha = 0.3f))
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notification Screen",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Device ID: $deviceId",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        )

        if(settingsResult != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Id: ${settingsResult.id}")
            Text(text = "Name: ${settingsResult.name}")
            Text(text = "Enabled: ${settingsResult.enabled}")

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
               /*navigator.navigateTo(
                   route = Screen.Settings.route,
                   navOptions = navOptions {
                       popUpTo(Screen.Home.route) {
                           inclusive = false
                       }
                   }
               )*/
                navigator.navigateWithData(
                    route = Screen.Settings.route,
                    data = Data(
                        data = notification,
                        key = "notification"
                    )
                )
                //navigator.popUntil(Screen.Home.route)
            }
        ) {
            Text(text = "Search button")
        }
    }
}

@Parcelize
data class NotificationUiModel(
    val title: String,
    val message: String
) : Parcelable

val notification = NotificationUiModel(
    title = "Notification title",
    message = "Notification message"
)

val Context.myDeviceId: String
    get() {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }