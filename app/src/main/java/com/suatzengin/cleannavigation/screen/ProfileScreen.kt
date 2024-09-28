package com.suatzengin.cleannavigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suatzengin.cleannavigation.component.ShimmerLayout
import com.suatzengin.cleannavigation.currentNavigator
import com.suatzengin.cleannavigation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val navigator = currentNavigator

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Blue.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(text = "Profile Screen")
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        navigator.navigateUp()
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Profile Screen", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Button(onClick = {
            navigator.navigateTo(Screen.Notification.route)
        }) {
            Text(text = "Notification button")
        }

        ShimmerLayout(modifier = Modifier)
    }
}