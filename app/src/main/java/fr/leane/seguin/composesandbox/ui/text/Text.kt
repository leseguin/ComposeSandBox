package fr.leane.seguin.composesandbox.ui.text

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.R
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

/**
 * https://www.youtube.com/watch?v=0mfCbXrYBPE&list=PLWz5rJ2EKKc_L3n1j4ajHjJ6QccFUvW1u&index=7
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun TextScreen() {
    val gradient = listOf(ComposeSandBoxTheme.colors.primary, ComposeSandBoxTheme.colors.secondary)

    var isTwoLine by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.lorem_ipsum),
            style = ComposeSandBoxTheme.typography.h1.copy(
                brush = Brush.horizontalGradient(gradient)
            ),
            modifier = Modifier
                //Add bouncing event
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(16.dp),
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (isTwoLine) 2 else Int.MAX_VALUE
        )

        Button(
            onClick = { isTwoLine = !isTwoLine },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = if (!isTwoLine) R.string.set_max_line_2 else R.string.set_max_line_undefined)
            )
        }
    }
}

@Preview
@Composable
fun TextScreenPreview() {
    TextScreen()
}