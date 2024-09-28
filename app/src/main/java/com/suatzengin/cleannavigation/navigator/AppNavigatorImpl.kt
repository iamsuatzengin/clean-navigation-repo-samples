package com.suatzengin.cleannavigation.navigator

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class AppNavigatorImpl : AppNavigator() {

    override val navigationEvent = MutableSharedFlow<NavigatorEvent>(
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    override fun navigateTo(route: String) {
        navigationEvent.tryEmit(
            NavigatorEvent.NavigateTo(route = route)
        )
    }

    override fun navigateTo(route: String, navOptions: NavOptions?) {
        navigationEvent.tryEmit(
            NavigatorEvent.NavigateWithOptions(
                route = route,
                navOptions = navOptions
            )
        )
    }

    override fun <T : Parcelable> navigateWithData(route: String, data: Data<T>, navOptions: NavOptions?) {
        navigationEvent.tryEmit(
            NavigatorEvent.NavigateWithData(
                route = route,
                data = data,
                navOptions = navOptions
            )
        )
    }

    override fun navigateBack() {
        navigationEvent.tryEmit(NavigatorEvent.NavigateBack)
    }

    override fun navigateUp() {
        navigationEvent.tryEmit(NavigatorEvent.NavigateUp)
    }

    override fun navigateAndPopUntil(route: String, until: String, inclusive: Boolean) {
        navigationEvent.tryEmit(
            NavigatorEvent.NavigateAndPopUntil(
                route = route,
                until = until,
                inclusive = inclusive
            )
        )
    }

    override fun popUntil(route: String) {
        navigationEvent.tryEmit(
            NavigatorEvent.PopUntil(route = route)
        )
    }

    override fun <T : Parcelable> saveData(data: T, key: String) {
        navController?.previousBackStackEntry?.savedStateHandle?.set(key = key, value = data)
    }

    override fun <T : Parcelable> restoreData(key: String): T? {
        return navController?.currentBackStackEntry?.savedStateHandle?.get<T>(key = key)
    }

    override fun <T : Parcelable> passData(data: T, key: String) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(key = key, value = data)
    }

    override fun <T : Parcelable> screenData(key: String): T? {
        return navController?.previousBackStackEntry?.savedStateHandle?.get<T>(key = key)
    }
}
