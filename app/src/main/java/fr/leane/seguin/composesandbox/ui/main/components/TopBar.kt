package fr.leane.seguin.composesandbox.ui.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun MainTopBar(title: String, openDrawerMenu: () -> Unit) {
    DefaultStyleTopBar({
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = openDrawerMenu) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
            Text(text = title, style = ComposeSandBoxTheme.typography.h1)
        }
    })
}

@Composable
fun DefaultStyleTopBar(content: @Composable () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(backgroundColor = ComposeSandBoxTheme.colors.primary, modifier = modifier) {
        content()
    }
}
