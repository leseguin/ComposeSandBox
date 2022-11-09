package fr.leane.seguin.composesandbox.ui.theme.type

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val SandBoxTypography = Typography(
    defaultFontFamily = RobotoFont,
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = UbuntuFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 22.sp
    ),
    button = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        fontWeight = FontWeight.Bold
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun TypographyPreview() {
    Column(Modifier.fillMaxWidth()) {
        Text(text = "SandBoxTypography Body1", style = SandBoxTypography.body1)
        Text(text = "SandBoxTypography Body2", style = SandBoxTypography.body2)
        Text(text = "SandBoxTypography h1", style = SandBoxTypography.h1)
        Text(text = "SandBoxTypography h2", style = SandBoxTypography.h2)
        Text(text = "SandBoxTypography button", style = SandBoxTypography.button)
    }
}