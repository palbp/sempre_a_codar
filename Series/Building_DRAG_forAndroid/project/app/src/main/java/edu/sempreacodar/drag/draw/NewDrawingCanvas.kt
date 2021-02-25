package edu.sempreacodar.drag.draw

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import edu.sempreacodar.drag.model.Drawing

@Composable
fun NewDrawingCanvas(modifier: Modifier, drawing: Drawing?) {

    Canvas(
        modifier = modifier,
        onDraw = {
            drawRoundRect(
                color = Color.Black,
                style = Stroke(5F),
                cornerRadius = CornerRadius(42F, 42F)
            )
        }
    )
}