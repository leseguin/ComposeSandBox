package fr.leane.seguin.composesandbox.ui.main

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import fr.leane.seguin.composesandbox.R


data class SandBoxNavigation(
    val navHostController: NavHostController,
    val startScreen: NavigationDirections
) {

    val directions = NavigationDirections.values().toList()

    /**
     * Is a mutableState because we need to update values (ex: Title in MenuNavigationScreen)
     * when currentDirection is updated
     */
    var currentDirection by mutableStateOf(startScreen)
        private set


    fun updateDirection(newDirection: NavigationDirections) {
        navHostController.navigate(newDirection.name, newDirection.navOptions)
        currentDirection = newDirection
    }
}

enum class NavigationDirections(@StringRes val resTitle: Int, val navOptions: NavOptions? = null) {
    LIST_DIRECTION(R.string.title_LIST_DIRECTION),
    CIRCULAR_BUTTON_DIRECTION(R.string.title_CIRCULAR_BUTTON_DIRECTION),
    SLIDER_DIRECTION(R.string.title_SLIDER_DIRECTION),
    TEXT_DIRECTION(R.string.title_TEXT_DIRECTION),
    ACCORDION_DIRECTION(R.string.title_ACCORDION_DIRECTION),
    MOVIES_DIRECTION(R.string.title_MOVIES_DIRECTION)
}
