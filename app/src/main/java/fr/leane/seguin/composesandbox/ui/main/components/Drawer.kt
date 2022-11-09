package fr.leane.seguin.composesandbox.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.R
import fr.leane.seguin.composesandbox.ui.main.NavigationDirections
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun Drawer(
    directions: List<NavigationDirections>,
    selectedScreen: NavigationDirections,
    setSelectedScreen: (selectScreen: NavigationDirections) -> Unit
) {
    DefaultStyleTopBar({
        Text(
            text = stringResource(id = R.string.app_name),
            style = ComposeSandBoxTheme.typography.h1,
            modifier = Modifier.padding(8.dp)
        )
    }, modifier = Modifier.padding(bottom = 12.dp))

    directions.forEach { navigationDirection ->
        DrawerItem(
            stringResource(id = navigationDirection.resTitle),
            isSelected = selectedScreen.name == navigationDirection.name,
            onClick = { setSelectedScreen(navigationDirection) },
            modifier = Modifier
                .padding(bottom = 4.dp)
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
private fun DrawerItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        if (isSelected) ComposeSandBoxTheme.colors.primary.copy(0.3f) else Color.White


    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = if (isSelected) ComposeSandBoxTheme.colors.primary else contentColorFor(
                backgroundColor
            )
        ),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(text = text, Modifier.fillMaxWidth())
    }
}