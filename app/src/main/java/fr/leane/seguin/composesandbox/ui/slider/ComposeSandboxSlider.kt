package fr.leane.seguin.composesandbox.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme
import fr.leane.seguin.composesandbox.ui.theme.primary300


@Composable
fun ComposeSandboxSlider(
    value: Float,
    setValue: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    barColor: Color = ComposeSandBoxTheme.colors.primaryVariant,
    thumb: (@Composable () -> Unit)? = null,
    thumbSize: Dp = 16.dp,
    barThickness: Dp = 4.dp,
    barShape: Shape = RoundedCornerShape(4.dp)
) {

    var offsetX by remember {
        mutableStateOf(0f)
    }

    val localDensity = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        val constraints = this
        val pixelStart = localDensity.run {
            constraints.minWidth.toPx()
        }
        val pixelEnd = localDensity.run {
            constraints.maxWidth.toPx() - thumbSize.toPx()
        }

        LaunchedEffect(pixelStart, pixelEnd, value) {
            offsetX = (value * pixelEnd) / range.endInclusive
        }

        SliderBar(
            value = (if (offsetX <= 0) 1f else offsetX),
            end = pixelEnd,
            color = barColor,
            barThickness = barThickness,
            barShape = barShape,
            thumbSize = thumbSize
        )

        ThumbSlider(
            thumb = { thumbModifier ->
                if (thumb == null) {
                    Thumb(
                        barColor,
                        thumbModifier.align(Alignment.CenterStart)
                    )
                } else {
                    Box(modifier = thumbModifier.align(Alignment.CenterStart)) {
                        thumb()
                    }
                }
            },
            thumbSize = thumbSize,
            offsetX = offsetX,
            setOffsetX = {
                offsetX = it
            },
            pixelStart = pixelStart,
            pixelEnd = pixelEnd,
            range = range,
            setValue = setValue
        )


    }
}


@Composable
private fun ThumbSlider(
    thumb: @Composable (Modifier) -> Unit,
    thumbSize: Dp,
    offsetX: Float,
    setOffsetX: (Float) -> Unit,
    pixelStart: Float,
    pixelEnd: Float,
    range: ClosedFloatingPointRange<Float>,
    setValue: (Float) -> Unit
) {
    val thumbModifier = Modifier
        .requiredSize(thumbSize)
        .offset { IntOffset(offsetX.toInt(), 0) }
        .draggable(
            rememberDraggableState(onDelta = { delta ->
                val pixelRange = pixelStart..pixelEnd
                if (offsetX + delta in pixelRange) {
                    setOffsetX((offsetX + delta))
                    val newValue = ((offsetX * range.endInclusive) / pixelEnd)
                    if (newValue in range) {
                        setValue(newValue)
                    }
                }
            }),
            Orientation.Horizontal

        )

    thumb(thumbModifier)
}

@Composable
private fun SliderBar(
    value: Float,
    end: Float,
    color: Color,
    barThickness: Dp,
    barShape: Shape,
    thumbSize: Dp
) {
    val weightStart = remember(value, end) {
        val weight = value / end
        if (weight > 0) {
            weight
        } else null
    }
    val weightEnd = remember(value, end) {
        val weight = (end - value) / end
        if (weight > 0) {
            weight
        } else null
    }

    Surface(
        shape = barShape,
        modifier = Modifier.padding(horizontal = thumbSize / 2)
    ) {
        Row(
            modifier = Modifier
                .height(barThickness)
        ) {
            weightStart?.let {
                Box(
                    modifier = Modifier
                        .background(color)
                        .fillMaxSize()
                        .weight(weightStart)
                )
            }

            weightEnd?.let {
                Box(
                    modifier = Modifier
                        .background(color.copy(0.5f))
                        .fillMaxSize()
                        .weight(weightEnd)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Thumb(color: Color, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        onClick = {},
        interactionSource = remember { MutableInteractionSource() },
        shape = CircleShape,
        color = color
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun ThumbBrightness(
    brightnessEnd: Float,
    modifier: Modifier = Modifier,
    centerSize: Dp = 16.dp
) {

    val gradient = remember(brightnessEnd) {
        val bright = if (brightnessEnd <= 0) {
            1f
        } else brightnessEnd
        Brush.radialGradient(
            colors = listOf(Color.White, Color.Transparent),
            center = Offset.Unspecified,
            radius = bright + centerSize.value + 8.dp.value,
            tileMode = TileMode.Decal
        )
    }

    val gradientBlue = remember(brightnessEnd) {
        val bright = if (brightnessEnd <= 0) {
            1f
        } else brightnessEnd
        Brush.radialGradient(
            colors = listOf(primary300.copy(alpha = 0.1f), Color.Transparent),
            center = Offset.Unspecified,
            radius = bright + centerSize.value + 8.dp.value,
            tileMode = TileMode.Decal
        )
    }

    Box(
        modifier = modifier
            .background(gradient, CircleShape)
    ) {
        Box(
            modifier = modifier
                .background(gradientBlue, CircleShape)
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(centerSize)
                .background(ComposeSandBoxTheme.colors.primaryVariant, CircleShape)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ComposeSandboxSliderPreview() {
    val range = 0f..100f
    val (value, setValue) = remember {
        mutableStateOf((range.start))
    }

    Column(Modifier.background(ComposeSandBoxTheme.colors.secondary)) {
        Text(text = value.toString())
        ComposeSandboxSlider(
            value = value,
            setValue = setValue,
            range = range,
            modifier = Modifier
                .padding(54.dp),
            thumb = {
                ThumbBrightness(brightnessEnd = value, modifier = Modifier.fillMaxSize())
            },
            barThickness = 8.dp,
            thumbSize = 54.dp,
        )
    }

}