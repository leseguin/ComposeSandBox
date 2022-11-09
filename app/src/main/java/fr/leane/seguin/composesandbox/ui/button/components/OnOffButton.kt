package fr.leane.seguin.composesandbox.ui.button.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme
import fr.leane.seguin.composesandbox.ui.theme.white

@Composable
fun OnOffButton(
    buttonSelected: OnOffButtonOptions,
    buttons: List<OnOffButtonOptions>,
    updateButton: (OnOffButtonOptions) -> Unit,
    modifier: Modifier = Modifier,
    colorSelected: Color = ComposeSandBoxTheme.colors.primary,
    colorUnSelected: Color = ComposeSandBoxTheme.colors.primaryVariant,
    typographySelected: TextStyle = ComposeSandBoxTheme.typography.body1,
    typographyUnSelected: TextStyle = ComposeSandBoxTheme.typography.body1,
) {
    Surface(
        shape = CircleShape,
        modifier = modifier.aspectRatio(1f),
        border = BorderStroke(1.dp, white)
    ) {
        Column {
            buttons.forEachIndexed { index, button ->
                OnOffButtonParts(
                    text = button.text,
                    onClick = {
                        button.onClick()
                        updateButton(button)
                    },
                    typography = if (button == buttonSelected) typographySelected else typographyUnSelected,
                    modifier = Modifier
                        .weight(1f)
                        .background(if (button == buttonSelected) colorSelected else colorUnSelected)
                )

                if (index < buttons.size - 1) {
                    Divider(color = white, thickness = 1.dp)
                }
            }
        }
    }
}

data class OnOffButtonOptions(val id: String, val text: String, val onClick: () -> Unit)

@Composable
private fun OnOffButtonParts(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    typography: TextStyle = ComposeSandBoxTheme.typography.body1
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Text(text = text, modifier = Modifier.align(Alignment.Center), style = typography)
    }
}

@Preview
@Composable
private fun OnOffButtonPreview() {
    val buttons = remember {
        listOf(
            OnOffButtonOptions("ON", "ON") {},
            //OnOffButtonOptions("MAYBE", "MAYBE"),
            OnOffButtonOptions("OFF", "OFF") {}
        )
    }

    val (buttonSelected, setButtonSelected) = remember {
        mutableStateOf(buttons.first())
    }

    OnOffButton(
        buttonSelected = buttonSelected,
        buttons = buttons,
        updateButton = setButtonSelected,
        modifier = Modifier.size(100.dp),
        typographySelected = ComposeSandBoxTheme.typography.body1.copy(fontWeight = FontWeight.W500)
    )
}