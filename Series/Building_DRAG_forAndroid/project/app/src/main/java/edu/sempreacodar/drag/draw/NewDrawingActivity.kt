package edu.sempreacodar.drag.draw

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import edu.sempreacodar.drag.databinding.ActivityDrawingBinding
import edu.sempreacodar.drag.draw.NewDrawingActivity.Companion.TIMER_VALUE_EXTRA
import edu.sempreacodar.drag.draw.NewDrawingActivity.Companion.WORD_EXTRA
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.ui.theme.DRAGTheme

/**
 * The drawing screen, implemented using Jetpack Compose.
 *
 * The activity receives two extras: one with the word to be drawn, identified by [WORD_EXTRA];
 * the other bearing the Int with the timer value in seconds, identified by [TIMER_VALUE_EXTRA]
 */
class NewDrawingActivity : AppCompatActivity() {

    companion object {
        const val WORD_EXTRA = "WORD_EXTRA_KEY"
        const val TIMER_VALUE_EXTRA = "TIMER_VALUE_EXTRA_KEY"
    }

    private val binding by lazy { ActivityDrawingBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<DrawingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DRAGTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NewDrawingActivityLayout(
                        word = getWordFromIntent(),
                        timer = getTimerValueFromIntent(),
                        drawing = viewModel.drawing.value
                    )
                }
            }
        }
    }

    /**
     * Helper method used to get the word to be drawn from the received intent
     */
    private fun getWordFromIntent() =
        if (intent.hasExtra(DrawingActivity.WORD_EXTRA)) intent.getStringExtra(DrawingActivity.WORD_EXTRA) as String
        else "Pintainho"

    /**
     * Helper method used to get the initial timer value from the received intent
     */
    private fun getTimerValueFromIntent() =
        GameTimer.fromSeconds(intent.getIntExtra(DrawingActivity.TIMER_VALUE_EXTRA, 60))
}
