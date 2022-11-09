package fr.leane.seguin.composesandbox.ui.explainer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import dev.jeziellago.compose.markdowntext.MarkdownText
import fr.leane.seguin.composesandbox.data.SandBoxItem
import fr.leane.seguin.composesandbox.ui.list.SimpleColumnScreen
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@Composable
fun Explainer(screenContent: @Composable (Modifier) -> Unit) {
    val (selectedTab, setSelectedTab) = remember {
        mutableStateOf(ExplainerOption.SCREEN_CONTENT)
    }
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExplainerFloatingButton(
                {
                    setSelectedTab(selectedTab.getReverse())
                },
                if (selectedTab == ExplainerOption.SCREEN_CONTENT) Icons.Rounded.Search else Icons.Rounded.Close
            )
        }
    ) {
        if (selectedTab == ExplainerOption.SCREEN_CONTENT) {
            screenContent(Modifier.padding(it))
        } else {
            ExplainerContent( "\n" +
                    "@Composable\n" +
                    "private fun ExplainerFloatingButton(onClick: () -> Unit, icon: ImageVector) {\n" +
                    "    Button(onClick = onClick) {\n" +
                    "        Icon(imageVector = icon, contentDescription = null)\n" +
                    "    }\n" +
                    "}",Modifier.padding(it))
        }
    }
}

@Composable
private fun ExplainerContent(markdown: String, modifier: Modifier = Modifier) {
    MarkdownText(
        modifier = modifier.fillMaxSize(),
        markdown = "```kotlin  \n $markdown \n ``` "
    )
}

@Composable
private fun ExplainerFloatingButton(onClick: () -> Unit, icon: ImageVector) {
    Button(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

private enum class ExplainerOption {
    SCREEN_CONTENT,
    EXPLAINER;

    fun getReverse(): ExplainerOption {
        return if (this == SCREEN_CONTENT) {
            EXPLAINER
        } else {
            SCREEN_CONTENT
        }
    }
}

@Preview
@Composable
fun ExplainerPreview() {
    val items = (0..20).map { i -> SandBoxItem.createSandBoxItem(i.toString()) }
    ComposeSandBoxTheme() {
        Explainer {
            SimpleColumnScreen(items, it)
        }
    }
}