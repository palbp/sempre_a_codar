package edu.sempreacodar.drag.draw

import android.os.Bundle
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

        val timerValue = getTimerValueFromIntent()
        binding.word.text = getWordFromIntent()
        binding.timer.text = timerValue.toString()

        viewModel.maybeStartTimer(timerValue)
        viewModel.timerValue.observe(this) {
            binding.timer.text = it.toString()
        }

        // TODO: Collect user input and update drawing accordingly via ViewModel
    }

    private fun getWordFromIntent() =
        if (intent.hasExtra(WORD_EXTRA)) intent.getStringExtra(WORD_EXTRA)
        else "Pintainho"

    private fun getTimerValueFromIntent() =
            GameTimer.fromSeconds(intent.getIntExtra(TIMER_VALUE_EXTRA, 60))
}
