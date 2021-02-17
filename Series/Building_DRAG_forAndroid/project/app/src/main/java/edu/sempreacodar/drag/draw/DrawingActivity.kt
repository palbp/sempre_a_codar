package edu.sempreacodar.drag.draw

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.sempreacodar.drag.databinding.ActivityDrawingBinding
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.TIMER_VALUE_EXTRA
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.WORD_EXTRA
import edu.sempreacodar.drag.draw.model.GameTimer

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

        viewModel.maybeStartTimer(getTimerValueFromIntent())

        // TODO: Collect user input and update drawing accordingly via ViewModel
    }

    /**
     * Renders the screen according to its current state. The UI state is fully specified to ensure
     * that the implementation is not dependent of any state transitions; it only depends on the
     * current state.
     */
    private fun renderUI() {
        binding.word.text = getWordFromIntent()
        binding.timer.text = viewModel.timerValue.value.toString()

        val timerValue = viewModel.timerValue.value
        fun isTimeRunningOut(): Boolean = viewModel.state.value == DrawingViewModel.State.DRAWING
                && timerValue != null
                && timerValue.toSeconds() < 10

        val timerColor = if (isTimeRunningOut()) theme.getColor(android.R.attr.textColor)
        else theme.getColor(android.R.attr.textColorHighlight)
        binding.timer.setTextColor(timerColor)

        // TODO: render according to the screen's state
    }

    /**
     * Helper method used to get the word to be drawn from the received intent
     */
    private fun getWordFromIntent() =
        if (intent.hasExtra(WORD_EXTRA)) intent.getStringExtra(WORD_EXTRA)
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
