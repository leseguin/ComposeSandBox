package fr.leane.seguin.composesandbox.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.data.SandBoxItem
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun SimpleColumnScreen(items: List<SandBoxItem>, modifier: Modifier = Modifier) {
    val color = ComposeSandBoxTheme.colors.primaryVariant
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        items.forEachIndexed { index, item ->
            SimpleRowItem(
                title = item.title,
                description = item.description,
                icon = item.icon,
                contentColor = if (index % 2 == 0) color else ComposeSandBoxTheme.colors.secondary,
                modifier = Modifier.padding(8.dp)
            )

            if (index < items.size - 1) {
                Divider()
            }
        }
    }
}

@Composable
private fun SimpleRowItem(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
) {
    Surface(
        contentColor = contentColor,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(text = title, style = ComposeSandBoxTheme.typography.h1)
                Text(text = description)
            }
        }
    }

}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun SimpleColumnScreenPreview() {
    SimpleColumnScreen(items = (0..20).map {
        SandBoxItem.createSandBoxItem(
            "$it"
        )
    }, modifier = Modifier.padding(bottom = 32.dp))
}