package fr.leane.seguin.composesandbox.ui.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.button.components.OnOffButton
import fr.leane.seguin.composesandbox.ui.button.components.OnOffButtonOptions
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun ButtonsScreen() {
    val buttons = rememberSaveable {
        listOf(
            OnOffButtonOptions("ON", "ON") {},
            OnOffButtonOptions("MAYBE", "MAYBE") {},
            OnOffButtonOptions("OFF", "OFF") {}
        )
    }
    val (buttonSelected, setButtonSelected) = remember(buttons) {
        mutableStateOf(buttons.first())
    }
    OnOffButton(
        buttonSelected = buttonSelected,
        buttons = buttons,
        updateButton = setButtonSelected,
        modifier = Modifier.padding(16.dp),
        typographySelected = ComposeSandBoxTheme.typography.h2.copy(color = Color.White),
        typographyUnSelected = ComposeSandBoxTheme.typography.h2
    )
}