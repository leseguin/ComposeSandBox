package fr.leane.seguin.composesandbox.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

data class SandBoxItem(val title: String, val description: String, val icon: ImageVector) {
    companion object {
        fun createSandBoxItem(
            title: String,
            icon: ImageVector = Icons.Default.Face
        ) = SandBoxItem(title, "description $title", icon)
    }
}