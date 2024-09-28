package com.suatzengin.cleannavigation.navigator

import androidx.navigation.NavOptions

sealed interface NavigatorEvent {
    data class NavigateTo(
        val route: String,
    ) : NavigatorEvent

    data class NavigateWithOptions(
        val route: String,
        val navOptions: NavOptions? = null
    ) : NavigatorEvent

    data object NavigateBack : NavigatorEvent

    data object NavigateUp : NavigatorEvent

    data class NavigateAndPopUntil(
        val route: String,
        val until: String,
        val inclusive: Boolean = false
    ) : NavigatorEvent

    data class PopUntil(val route: String) : NavigatorEvent

    data class NavigateWithData<T>(
        val route: String,
        val data: Data<T>,
        val navOptions: NavOptions? = null,
    ) : NavigatorEvent
}

data class Data<T>(
    val data: T,
    val key: String
)