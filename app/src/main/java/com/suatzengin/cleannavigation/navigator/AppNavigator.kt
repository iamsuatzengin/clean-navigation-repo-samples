package com.suatzengin.cleannavigation.navigator

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AppNavigator {
    abstract val navigationEvent: MutableSharedFlow<NavigatorEvent>
    var navController: NavHostController? = null

    abstract fun navigateTo(route: String)

    abstract fun navigateTo(route: String, navOptions: NavOptions? = null)

    abstract fun <T : Parcelable> navigateWithData(
        route: String,
        data: Data<T>,
        navOptions: NavOptions? = null
    )

    abstract fun navigateBack()

    abstract fun navigateUp()

    abstract fun navigateAndPopUntil(route: String, until: String, inclusive: Boolean = false)

    abstract fun popUntil(route: String)

    abstract fun <T : Parcelable> saveData(data: T, key: String)
    abstract fun <T : Parcelable> restoreData(key: String): T?

    abstract fun <T : Parcelable> passData(data: T, key: String)
    abstract fun <T : Parcelable> screenData(key: String): T?

    suspend fun handleNavigationEvents(navHostController: NavHostController) {
        this.navController = navHostController

        navigationEvent.collect { event ->
            when (event) {
                NavigatorEvent.NavigateBack -> {
                    navController?.popBackStack()
                }

                is NavigatorEvent.NavigateTo -> {
                    navController?.navigate(event.route)
                    println("event navigateTo called")
                }

                NavigatorEvent.NavigateUp -> {
                    navController?.navigateUp()
                }

                is NavigatorEvent.NavigateAndPopUntil -> {
                    navController?.navigate(
                        route = event.route
                    ) {
                        popUpTo(route = event.until) {
                            inclusive = event.inclusive
                        }
                    }
                }

                is NavigatorEvent.PopUntil -> {
                    navController?.popUntil(route = event.route)
                }

                is NavigatorEvent.NavigateWithOptions -> {
                    navController?.navigate(
                        route = event.route,
                        navOptions = event.navOptions
                    )
                }

                is NavigatorEvent.NavigateWithData<*> -> {
                    passData(data = event.data.data as Parcelable, key = event.data.key)

                    navController?.navigate(
                        route = event.route,
                        navOptions = event.navOptions
                    )
                }
            }
        }
    }

    private fun NavController.popUntil(route: String) {
        while (
            currentBackStackEntry?.destination?.route != route
            && popBackStack()
        ) {
        }
    }
}

