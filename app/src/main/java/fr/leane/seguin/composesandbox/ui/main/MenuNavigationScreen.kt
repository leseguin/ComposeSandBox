package fr.leane.seguin.composesandbox.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import fr.leane.seguin.composesandbox.ui.main.components.Drawer
import fr.leane.seguin.composesandbox.ui.main.components.MainTopBar
import kotlinx.coroutines.launch

@Composable
fun MenuNavigationScreen() {
    val navController = rememberNavController()

    val sandBoxNavigation = remember(navController) {
        SandBoxNavigation(navController)
    }

    MenuNavigationScaffold(sandBoxNavigation = sandBoxNavigation)
}

@Composable
private fun MenuNavigationScaffold(sandBoxNavigation: SandBoxNavigation) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    /**
     * When sandBoxNavigation.currentDirection is updated
     * the title is also updated
     * How to do : cf SandBoxNavigation class
     */
    val title by remember(sandBoxNavigation.currentDirection) {
        mutableStateOf(sandBoxNavigation.currentDirection.name)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(sandBoxNavigation.directions, sandBoxNavigation.currentDirection) {
                sandBoxNavigation.updateDirection(it)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        topBar = {
            MainTopBar(title) {
                scope.launch {
                    if (scaffoldState.drawerState.isOpen) {
                        scaffoldState.drawerState.close()
                    } else {
                          scaffoldState.drawerState.open()
                    }
                }
            }
        }
    ) { paddingValues ->
        NavigationComponent(
            modifier = Modifier.padding(paddingValues),
            navigation = sandBoxNavigation
        )
    }
}

@Preview
@Composable
private fun PreviewMenuNavigationScreen() {
    MenuNavigationScreen()
}