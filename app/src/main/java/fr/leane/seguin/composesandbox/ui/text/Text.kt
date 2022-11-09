package fr.leane.seguin.composesandbox.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.jeziellago.compose.markdowntext.MarkdownText
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun TextExplainer(text: String, infoContent: String, modifier: Modifier = Modifier) {

    var isInfoSelected by remember {
        mutableStateOf(false)
    }

    if (isInfoSelected) {
        DialogExplainer(
            onDismiss = { isInfoSelected = false }, text = infoContent
        )
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = ComposeSandBoxTheme.typography.subtitle1,
            color = ComposeSandBoxTheme.colors.primaryVariant,
            modifier = modifier.padding(4.dp)
        )

        IconButton(onClick = { isInfoSelected = true }) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = ComposeSandBoxTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
fun DialogExplainer(onDismiss: () -> Unit, text: String) {
    Dialog(onDismissRequest = onDismiss) {
        Surface() {
            ExplainerContent(markdown = text)
        }
    }
}

@Composable
private fun ExplainerContent(markdown: String, modifier: Modifier = Modifier) {
    MarkdownText(
        modifier = modifier.fillMaxWidth(),
        markdown = "```kotlin  \n $markdown \n ``` "
    )
}