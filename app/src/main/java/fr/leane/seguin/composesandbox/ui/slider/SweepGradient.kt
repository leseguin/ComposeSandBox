package fr.leane.seguin.composesandbox.ui.slider

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.leane.seguin.composesandbox.ui.theme.ComposeSandBoxTheme
import kotlin.math.*


@Composable
fun SweepGradient(modifier: Modifier = Modifier) {
    val (progress, setProgress) = remember {
        mutableStateOf(1f)
    }

    val angleInDegrees by remember(progress) {
        mutableStateOf((progress * 260).toDouble() + 50)
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val constraints = this


        Column {
            SweepGradientContent(setProgress, progress, size = constraints.maxWidth)
            Slider(
                value = progress,
                onValueChange = setProgress,
                modifier = Modifier.padding(horizontal = 36.dp)
            )
        }

    }
}

@Composable
private fun SweepGradientContent(
    updateProgress: (Float) -> Unit,
    progressPercentage: Float,
    angleStart: Float = 50f,
    angleEnd: Float = 260f,
    size: Dp,
) {

    var dragPosition by remember {
        mutableStateOf(Offset.Zero)
    }
    Canvas(
        modifier = Modifier
            .size(size)
            .padding(36.dp)
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    dragPosition += dragAmount
                    Log.d("SweepGradientContent", "detectDragGestures $dragAmount")
                }
            }
    ) {


        rotate(90f) {
            val constraintsCanvas = this
            val (indicatorX, indicatorY) = constraintsCanvas.calculateIndicatorPosition(dragPosition)
            val constraints = this
            /*
            drawArc(
                color = Color.LightGray,
                startAngle = 50f,
                sweepAngle = 260f,
                useCenter = false,
                size = constraints.size,
                style = Stroke(30.dp.toPx(), cap = StrokeCap.Round),
            )
            */

            drawArc(
                brush = Brush.sweepGradient(
                    listOf(
                        Color.Green,
                        Color.Green,
                        Color.Yellow,
                        Color.Yellow,
                        Color.Red,
                        Color.Red,
                    )
                ),
                angleStart,
                progressPercentage * angleEnd,
                false,
                size = Size(constraints.size.width, constraints.size.height),
                style = Stroke(30.dp.toPx(), cap = StrokeCap.Round),
            )


            translate(indicatorX, indicatorY) {
                updateProgress(getProgressFromOffset(Offset(indicatorX, indicatorY)))
                drawCircle(
                    color = Color.Red,
                    radius = indicatorCircleRadius(),
                    style = Stroke(10f),
                )
            }
        }
    }
}


private fun DrawScope.getProgressFromOffset(offset: Offset): Float {
    val dragXOnCanvas = offset.x
    val dragYOnCanvas = offset.y
    val radius = outerCircleRadius()
    val angle = acos(dragXOnCanvas / radius)
    val adjustedAngle = if (dragYOnCanvas < 0) angle * -1 else angle
    val angleDegrees = angleRadToDegrees360(adjustedAngle)
    val angleDegreesAdjusted = angleDegrees

    val progress = angleDegreesAdjusted / 260
    val progressDetail = if (progress > 1) {
        abs(1 - progress)
    } else progress
    /*
    Log.d("angle", angle.toString())
    Log.d("angleDegrees", angleDegrees.toString())
    Log.d("angleDegreesAdjusted", angleDegreesAdjusted.toString())
    Log.d("progress", progress.toString())
    Log.d("progressDetail", progressDetail.toString())
     */
    return progressDetail.toFloat()
}

private fun getPointXOnTheCircle(angleInDegrees: Double, radius: Float): Float {
    return -(radius * sin(Math.toRadians(angleInDegrees)).toFloat())
}

private fun getPointYOnTheCircle(angleInDegrees: Double, radius: Float): Float {
    return (radius * cos(Math.toRadians(angleInDegrees)).toFloat())
}

private fun angleRadToDegrees360(angle: Float) = (angle * (180 / PI)) + 180

private fun angleDegrees360ToRad(angle: Float): Float = (angle / (180 / PI)).toFloat()

private fun DrawScope.calculateIndicatorPosition(dragPosition: Offset): Offset {
    Log.d("dragPosition", dragPosition.toString())
    drawCircle(color = Color.LightGray, radius = 10f, center = dragPosition)
    val dragXOnCanvas = dragPosition.x - size.height
    val dragYOnCanvas = dragPosition.y - size.height
    val radius = radiusForPoint(dragXOnCanvas, dragYOnCanvas)
    val angle = acos(dragXOnCanvas / radius)
    val adjustedAngle = (if (dragYOnCanvas < 0) angle * -1 else angle)
    val xOnCircle = outerCircleRadius() * cos(adjustedAngle)
    val yOnCircle = outerCircleRadius() * sin(adjustedAngle)

    Log.d("dragPosition on Circle", "$xOnCircle and $yOnCircle")
    drawCircle(color = Color.Blue, radius = 10f, center = Offset(xOnCircle, yOnCircle))
    return Offset(xOnCircle, yOnCircle)
}

fun radiusForPoint(x: Float, y: Float): Float {
    return sqrt(x.pow(2) + y.pow(2))
}

fun DrawScope.indicatorCircleRadius(): Float {
    return outerCircleRadius() / 12
}

private fun DrawScope.outerCircleRadius(): Float {
    return (horizontalCenter).coerceAtMost(verticalCenter)
}

private val DrawScope.horizontalCenter get() = size.width / 2
private val DrawScope.verticalCenter get() = size.height / 2

@Preview
@Composable
fun SweepGradientPreview() {
    ComposeSandBoxTheme {
        SweepGradient()
    }
}