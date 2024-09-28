package com.suatzengin.cleannavigation.navigation

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suatzengin.cleannavigation.currentNavigator
import com.suatzengin.cleannavigation.screen.home.HomeScreen
import com.suatzengin.cleannavigation.screen.NotificationScreen
import com.suatzengin.cleannavigation.screen.NotificationUiModel
import com.suatzengin.cleannavigation.screen.ProfileScreen
import com.suatzengin.cleannavigation.screen.SearchScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Search.route) {
            SearchScreen()
        }

        composable(route = Screen.Notification.route) {
            NotificationScreen()
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }

        composable(route = Screen.Settings.route) {
            val navigator = currentNavigator

            val notificationUiModel = remember {
                navigator.screenData<NotificationUiModel>("notification")
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Settings Screen")

                if(notificationUiModel != null) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Title: ${notificationUiModel.title}")
                    Text(text = "Message: ${notificationUiModel.message}")

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = {
                        navigator.saveData(
                            data = SettingResultModel(
                                id = 84,
                                name = "Dark mode enabled",
                                enabled = true
                            ),
                            key = "result"
                        )

                        navigator.navigateUp()
                    }
                ) {
                    Text("Geri dön la")
                }
            }
        }
    }
}

@Parcelize
data class SettingResultModel(
    val id: Int,
    val name: String,
    val enabled: Boolean
) : Parcelable