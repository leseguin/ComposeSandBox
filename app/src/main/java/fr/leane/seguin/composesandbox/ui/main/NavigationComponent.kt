package fr.leane.seguin.composesandbox.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.leane.seguin.composesandbox.data.SandBoxItem
import fr.leane.seguin.composesandbox.ui.button.ButtonsScreen
import fr.leane.seguin.composesandbox.ui.list.SimpleColumnScreen
import fr.leane.seguin.composesandbox.ui.slider.SliderScreen

@Composable
fun NavigationComponent(
    navigation: SandBoxNavigation,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navigation.navHostController,
        startDestination = NavigationDirections.LIST_DIRECTION.name
    ) {
        composable(NavigationDirections.LIST_DIRECTION.name) {
            val items = (0..20).map { i -> SandBoxItem.createSandBoxItem("Header $i") }
            SimpleColumnScreen(items)
        }

        composable(NavigationDirections.BUTTONS_DIRECTION.name) {
            ButtonsScreen()
        }

        composable(NavigationDirections.SLIDER_DIRECTION.name) {
            SliderScreen()
        }
    }
}