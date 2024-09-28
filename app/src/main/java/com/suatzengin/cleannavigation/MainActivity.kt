package com.suatzengin.cleannavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.suatzengin.cleannavigation.navigation.AppNavigation
import com.suatzengin.cleannavigation.navigator.AppNavigator
import com.suatzengin.cleannavigation.navigator.AppNavigatorImpl
import com.suatzengin.cleannavigation.service.NotificationsServiceScreen
import com.suatzengin.cleannavigation.ui.theme.CleanNavigationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanNavigationTheme {
                val navController = rememberNavController()
                val navigator = remember { AppNavigatorImpl() }

                LaunchedEffect(key1 = Unit) {
                    navigator.handleNavigationEvents(navController)
                }

                NotificationsServiceScreen()

                /*CompositionLocalProvider(
                    LocalAppNavigator provides navigator
                ) {
                    AppNavigation(
                        navController = navController
                    )
                }*/
            }
        }
    }
}

val LocalAppNavigator = staticCompositionLocalOf<AppNavigator> { error("error navigator") }

val currentNavigator
    @Composable
    @ReadOnlyComposable
    get() = LocalAppNavigator.current