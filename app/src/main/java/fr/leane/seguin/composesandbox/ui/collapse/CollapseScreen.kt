package fr.leane.seguin.composesandbox.ui.collapse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

/**
 * Exercise : Only one item extended
 */

@Composable
fun CollapseScreen() {
    val sublist = (0..4).map { index ->
        AccordionItem("SubHeader $index", (0..4).mapNotNull {
            AccordionItem("Description of $index", emptyList())
        })
    }
    val items = (1..6).map { index ->
        AccordionItem("Header $index", if (index % 3 == 0) emptyList() else sublist)
    }
    Accordion(items)
}

@Composable
private fun Accordion(items: List<AccordionItem>, modifier: Modifier = Modifier) {
    LazyColumn(modifier, contentPadding = PaddingValues(vertical = 16.dp)) {
        items(items) { accordion ->
            AccordionTitle(accordionItem = accordion)
        }
    }
}

@Composable
private fun AccordionTitle(accordionItem: AccordionItem, modifier: Modifier = Modifier) {
    var displayChildren by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { displayChildren = !displayChildren }
    ) {
        Column(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = accordionItem.title,
                    style = ComposeSandBoxTheme.typography.button
                )
                if (accordionItem.items.isNotEmpty()) {
                    Icon(
                        imageVector = if (displayChildren) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(visible = displayChildren) {
                Column(Modifier.padding(start = 12.dp)) {
                    accordionItem.items.forEach { accordionChild ->
                        AccordionTitle(accordionItem = accordionChild)
                    }
                }
            }
        }
    }
}

private data class AccordionItem(val title: String, val items: List<AccordionItem>)

@Preview
@Composable
fun CollapseScreenPreview() {
    CollapseScreen()
}
