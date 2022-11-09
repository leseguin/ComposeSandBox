package fr.leane.seguin.composesandbox.ui.button.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme
import fr.leane.seguin.composesandbox.ui.theme.lightBlack


@Composable
fun VerticalButton(
    list: List<VerticalButtonData>,
    modifier: Modifier = Modifier,
    selectedButton: VerticalButtonData? = null,
    setSelectedButton: ((VerticalButtonData) -> Unit)? = null,
) {
    Column(
        modifier
    ) {
        list.forEachIndexed { index, item ->
            VerticalButtonItem(
                icon = item.icon,
                title = item.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onClick = {
                    setSelectedButton?.invoke(item)
                },
                isSelected = selectedButton == item
            )

            if (index < list.size - 1) {
                Divider()
            }
        }
    }
}

data class VerticalButtonData(val icon: ImageVector, val title: String, val onClick: () -> Unit)


@Composable
private fun VerticalButtonItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    colorSelected: Color = ComposeSandBoxTheme.colors.primary,
    colorUnselected: Color = ComposeSandBoxTheme.colors.primaryVariant,
) {

    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) colorSelected else colorUnselected
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 24.dp)
        )
        Text(text = title.uppercase(), style = ComposeSandBoxTheme.typography.h2)
    }
}

@Preview
@Composable
fun VerticalButtonPreview() {
    val data = listOf(
        VerticalButtonData(Icons.Default.Menu, "Totale") {

        },
        VerticalButtonData(Icons.Default.Menu, "Partiel") {

        },
        VerticalButtonData(Icons.Default.Menu, "Désactivé") {
            // LLAAL
        },
    )

    val (selectedButton, setSelectedButton) = remember {
        mutableStateOf(data.first())
    }

    ComposeSandBoxTheme {
        VerticalButton(
            list = data,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .background(lightBlack),
            selectedButton = selectedButton,
            setSelectedButton = setSelectedButton,
        )
    }

}