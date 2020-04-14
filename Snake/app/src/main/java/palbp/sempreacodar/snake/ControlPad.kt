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
import palbp.sempreacodar.snake.physics.Vector2D


/**
 * The game control pad.
 */
@MainThread
class ControlPad(ctx: Context, attrs: AttributeSet?) : View(ctx, attrs) {

    interface ControlListener {
        fun onChangeDirection(direction: Vector2D)
    }

    var listener: ControlListener? = null

    private lateinit var smallCenter: Vector2D
    private var smallRadius: Float = 0f
    private lateinit var bigCenter: Vector2D
    private var bigRadius: Float = 0f

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
            smallCenter = Vector2D((right - left)/2f, (bottom - top)/2f)
            smallRadius = width / 4f
            bigRadius = width / 2f
            bigCenter = Vector2D(width / 2f, height / 2f)
        }
    }

    /**
     * Method called on the draw phase of the rendering algorithm
     */
    override fun onDraw(canvas: Canvas) {
        val brushes = if (isPressed) pressedBrushes else notPressedBrushes
        canvas.drawCircle(bigCenter.x, bigCenter.y, bigRadius, brushes.first)
        canvas.drawCircle(smallCenter.x, smallCenter.y, smallRadius, brushes.second)
    }

    /**
     * Method called to handle touch events
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        fun computeDistance(event: MotionEvent): Vector2D {
            val distanceVector = Vector2D(event.x, event.y) - bigCenter
            return if (distanceVector.magnitude() + smallRadius < bigRadius)
                distanceVector
            else
                distanceVector.unit() * (bigRadius - smallRadius)
        }

        fun computeSmallCenter(distance: Vector2D): Vector2D {
            return Vector2D(bigCenter.x, bigCenter.y) + distance
        }

        var direction = computeDistance(event)
        smallCenter = when (event.action) {
            MotionEvent.ACTION_DOWN -> { isPressed = true; computeSmallCenter(direction) }
            MotionEvent.ACTION_UP -> { isPressed = false; direction = Vector2D.NullVector; bigCenter }
            else -> computeSmallCenter(direction)
        }
        invalidate()
        listener?.onChangeDirection(direction)

        return true
    }
}