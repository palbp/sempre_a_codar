package edu.sempreacodar.drag.draw

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.sempreacodar.drag.databinding.ActivityDrawingBinding
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.TIMER_VALUE_EXTRA
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.WORD_EXTRA
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.model.Point

/**
 * The drawing screen.
 *
 * The activity receives two extras: one with the word to be drawn, identified by [WORD_EXTRA];
 * the other bearing the Int with the timer value in seconds, identified by [TIMER_VALUE_EXTRA]
 */
class DrawingActivity : AppCompatActivity() {

    companion object {
        const val WORD_EXTRA = "WORD_EXTRA_KEY"
        const val TIMER_VALUE_EXTRA = "TIMER_VALUE_EXTRA_KEY"
    }

    private val binding by lazy { ActivityDrawingBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.timerValue.observe(this) { renderUI() }
        viewModel.state.observe(this) { renderUI() }
        viewModel.drawing.observe(this) { renderUI() }

        viewModel.maybeStartTimer(getTimerValueFromIntent())
    }

    /**
     * Renders the screen according to its current state. The UI state is fully specified to ensure
     * that the implementation is not dependent of any state transitions; it only depends on the
     * current state.
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun renderUI() {
        binding.word.text = getWordFromIntent()
        binding.timer.text = viewModel.timerValue.value.toString()

        val timerValue = viewModel.timerValue.value
        fun isTimeRunningOut(): Boolean = viewModel.state.value != DrawingViewModel.State.INITIALIZED
                && timerValue != null
                && timerValue.toSeconds() < 10

        val timerColor = if (!isTimeRunningOut()) theme.getColor(android.R.attr.textColor)
        else theme.getColor(android.R.attr.textColorHighlight)
        binding.timer.setTextColor(timerColor)

        viewModel.drawing.value?.let { binding.drawingCanvas.drawing = it }

        val touchListener: ((View, MotionEvent) -> Boolean)? =
                if (viewModel.state.value == DrawingViewModel.State.DRAWING)
                    { _, event ->
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> { viewModel.startLineAt(Point(event.x, event.y)); true }
                            MotionEvent.ACTION_MOVE -> { viewModel.addToLine(Point(event.x, event.y)); true }
                            MotionEvent.ACTION_UP -> { viewModel.endLineAt(Point(event.x, event.y)); true }
                            else -> false
                        }
                    }
                else null

        binding.drawingCanvas.setOnTouchListener(touchListener)
    }

    /**
     * Helper method used to get the word to be drawn from the received intent
     */
    private fun getWordFromIntent() =
        if (intent.hasExtra(WORD_EXTRA)) intent.getStringExtra(WORD_EXTRA) as String
        else "Pintainho"

    /**
     * Helper method used to get the initial timer value from the received intent
     */
    private fun getTimerValueFromIntent() =
            GameTimer.fromSeconds(intent.getIntExtra(TIMER_VALUE_EXTRA, 60))
}

/**
 * Helper extension function used to get the color with the given attribute id from the theme.
 */
private fun Resources.Theme.getColor(attrId: Int): Int {
    val typedValue = TypedValue()
    resolveAttribute(attrId, typedValue, true)
    return typedValue.data
}
