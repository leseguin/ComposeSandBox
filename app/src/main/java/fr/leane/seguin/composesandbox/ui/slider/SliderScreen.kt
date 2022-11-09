package fr.leane.seguin.composesandbox.ui.slider

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.helpers.FileHelper
import fr.leane.seguin.composesandbox.ui.dialog.TitleExplainer
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderScreen() {
    val range = 0f..100f
    val (value, setValue) = remember {
        mutableStateOf((range.start))
    }

    val spacing = 16.dp

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleExplainer(text = "ComposeSandboxSlider", infoContent = FileHelper.readFile(""))
        Text(text = value.toString(), style = ComposeSandBoxTheme.typography.h2)

        Spacer(modifier = Modifier.height(spacing))
        ComposeSandboxSlider(value = value, setValue = setValue, range = range)

        Spacer(modifier = Modifier.height(spacing))
        ComposeSandboxSlider(
            value = value,
            setValue = setValue,
            range = range,
            thumb = {
                ThumbBrightness(
                    brightnessEnd = value,
                    modifier = Modifier.fillMaxSize()
                )
            },
            barThickness = 8.dp,
            thumbSize = 30.dp,
            barShape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(spacing))


        Spacer(modifier = Modifier.height(spacing))

        ComposeSandboxSlider(value = value, setValue = setValue, range = range, thumb = {
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Red))
                    .fillMaxSize()
            )
        })

        Spacer(modifier = Modifier.height(spacing))

        ComposeSandboxSlider(value = value, setValue = setValue, range = range, thumb = {

        })

        Spacer(modifier = Modifier.height(spacing))

        Slider(value = value, onValueChange = setValue, valueRange = 0f..100f)

        Spacer(modifier = Modifier.height(spacing))
    }

}
