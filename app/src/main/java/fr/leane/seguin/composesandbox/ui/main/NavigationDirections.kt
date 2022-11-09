package fr.leane.seguin.composesandbox.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions


data class SandBoxNavigation(val navHostController: NavHostController) {

    val directions = NavigationDirections.values().toList()

    /**
     * Is a mutableState because we need to update values (ex: Title in MenuNavigationScreen)
     * when currentDirection is updated
     */
    var currentDirection by mutableStateOf(directions.first())
        private set


    fun updateDirection(newDirection: NavigationDirections) {
        navHostController.navigate(newDirection.name, newDirection.navOptions)
        currentDirection = newDirection
    }
}

enum class NavigationDirections(val navOptions: NavOptions? = null) {
    LIST_DIRECTION,
    BUTTONS_DIRECTION,
    SLIDER_DIRECTION,
}
