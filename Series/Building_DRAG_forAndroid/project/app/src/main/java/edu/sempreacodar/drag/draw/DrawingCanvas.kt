package edu.sempreacodar.drag.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import edu.sempreacodar.drag.draw.model.Drawing
import edu.sempreacodar.drag.draw.model.Line

/**
 * Custom view used to draw on the screen.
 */
class DrawingCanvas(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : View(ctx, attrs, defStyleAttr) {

    constructor(ctx: Context, attrs: AttributeSet) : this(ctx, attrs, 0)
    constructor(ctx: Context) : this(ctx, null, 0)

    private val brush by lazy {
        Paint().apply {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.textColor, typedValue, true)
            color = typedValue.data
            strokeWidth = 5F
            style = Paint.Style.STROKE
        }
    }

    /**
     * The associated model
     */
    var drawing: Drawing = Drawing()
        set(value) { field = value; invalidate() }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(0F, 0F, width.toFloat(), height.toFloat(), 42F, 42F, brush)
        drawDrawing(canvas)
    }

    private fun drawDrawing(canvas: Canvas) = drawing.lines.forEach {
        drawLine(it, canvas)
    }

    private fun drawLine(line: Line, canvas: Canvas) {
        if (line.points.isEmpty())
            return

        var lastPoint = line.points.first()
        for(point in line.points) {
            canvas.drawLine(lastPoint.x, lastPoint.y, point.x, point.y, brush)
            lastPoint = point
        }
    }
}