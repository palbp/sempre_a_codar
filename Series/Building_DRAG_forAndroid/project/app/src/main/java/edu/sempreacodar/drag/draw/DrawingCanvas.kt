package edu.sempreacodar.drag.draw

import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.Line
import edu.sempreacodar.drag.model.forEach

/**
 * Composable used to specify the drawing area
 */
@Composable
fun NewDrawingCanvas(modifier: Modifier, drawing: Drawing?) {

    val color = MaterialTheme.colors.onBackground

    fun drawLine(scope: DrawScope, line: Line) {
        if (line.points.isEmpty())
            return

        var lastPoint = line.points.first()
        for(point in line.points) {
            scope.drawLine(
                color,
                Offset(lastPoint.x, lastPoint.y),
                Offset(point.x, point.y),
                strokeWidth = 5.0F
            )
            lastPoint = point
        }

    }

    Canvas(
        modifier = modifier,
        onDraw = {
            drawRoundRect(
                color = color,
                style = Stroke(5F),
                cornerRadius = CornerRadius(42F, 42F)
            )

            drawing?.let {
                drawing.forEach { drawLine(this, it) }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOfNewDrawingCanvas() {
    NewDrawingCanvas(
        Modifier,
        drawing = null
    )
}