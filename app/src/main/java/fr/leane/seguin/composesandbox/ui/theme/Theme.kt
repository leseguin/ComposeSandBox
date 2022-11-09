package fr.leane.seguin.composesandbox.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import fr.leane.seguin.composesandbox.ui.theme.type.SandBoxTypography

private val LightColorPalette = lightColors(
    primary = primary300,
    primaryVariant = primary700,
    secondary = complementary500,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

object ComposeSandBoxTheme {
    val typography = SandBoxTypography
    val colors = LightColorPalette
}

@Composable
fun ComposeSandBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        //DarkColorPalette
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = SandBoxTypography,
        shapes = Shapes,
        content = content
    )
}