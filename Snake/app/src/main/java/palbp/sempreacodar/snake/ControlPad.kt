package palbp.sempreacodar.snake

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.MainThread
import androidx.core.content.res.ResourcesCompat.getColor
import palbp.sempreacodar.snake.physics.Location

/**
 * The game control pad.
 */
@MainThread
class ControlPad(ctx: Context, attrs: AttributeSet?) : View(ctx, attrs) {

    private lateinit var interiorCenter: Location

    private val notPressedBrushes: Pair<Paint, Paint> = Pair(
        Paint().apply { color = getColor(resources, R.color.colorPadBackground, ctx.theme) },
        Paint().apply { color = getColor(resources, R.color.colorPadForeground, ctx.theme) }
    )

    private val pressedBrushes: Pair<Paint, Paint> = Pair(
        Paint().apply { color = getColor(resources, R.color.colorPadBackgroundAccent, ctx.theme) },
        Paint().apply { color = getColor(resources, R.color.colorPadForegroundAccent, ctx.theme) }
    )

    /**
     * Method called on the measure phase of the rendering algorithm
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // The control will have equal width and height. Because its width is constrained, it is
        // used as the control's side.
        val width = paddingLeft + paddingRight + suggestedMinimumWidth
        val side = resolveSizeAndState(width, widthMeasureSpec, 1)
        setMeasuredDimension(side, side)
    }
    /**
     * Method called whenever the size of the control is changed
     */
    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (changed) {
            interiorCenter = Location((right - left)/2f, (bottom - top)/2f)
        }
    }

    /**
     * Method called on the draw phase of the rendering algorithm
     */
    override fun onDraw(canvas: Canvas) {
        val brushes = if (isPressed) pressedBrushes else notPressedBrushes
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, brushes.first)
        canvas.drawCircle(interiorCenter.x, interiorCenter.y, width / 4f, brushes.second)
    }

    /**
     * Method called to handle touch events
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        fun computeCenter(event: MotionEvent): Location {
            return Location(event.x, event.y)
        }
        interiorCenter = when (event.action) {
            MotionEvent.ACTION_DOWN -> { isPressed = true; computeCenter(event) }
            MotionEvent.ACTION_UP -> { isPressed = false; Location(width/2f, height/2f) }
            else -> computeCenter(event)
        }
        invalidate()
        return true
    }
}