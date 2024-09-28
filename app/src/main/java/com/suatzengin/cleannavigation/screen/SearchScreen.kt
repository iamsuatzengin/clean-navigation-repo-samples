package com.suatzengin.cleannavigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.suatzengin.cleannavigation.currentNavigator
import com.suatzengin.cleannavigation.navigation.Screen

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val navigator = currentNavigator

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Green.copy(alpha = 0.3f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Search Screen", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Button(onClick = {
            navigator.popUntil(route = Screen.Home.route)
        }) {
            Text(text = "Pop until")
        }
    }
}