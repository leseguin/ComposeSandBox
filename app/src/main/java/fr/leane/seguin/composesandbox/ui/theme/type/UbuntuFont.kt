package fr.leane.seguin.composesandbox.ui.theme.type

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import fr.leane.seguin.composesandbox.R

val UbuntuFont = FontFamily(
    Font(R.font.ubuntu_regular),
    Font(R.font.ubuntu_italic, style = FontStyle.Italic),
    Font(R.font.ubuntu_bold, weight = FontWeight.Bold)
)