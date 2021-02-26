package edu.sempreacodar.drag.draw

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.TIMER_VALUE_EXTRA
import edu.sempreacodar.drag.draw.DrawingActivity.Companion.WORD_EXTRA
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.model.Point
import edu.sempreacodar.drag.ui.theme.DRAGTheme

/**
 * The drawing screen, implemented using Jetpack Compose.
 *
 * The activity receives two extras: one with the word to be drawn, identified by [WORD_EXTRA];
 * the other bearing the Int with the timer value in seconds, identified by [TIMER_VALUE_EXTRA]
 */
class DrawingActivity : AppCompatActivity() {

    companion object {
        const val WORD_EXTRA = "WORD_EXTRA_KEY"
        const val TIMER_VALUE_EXTRA = "TIMER_VALUE_EXTRA_KEY"
    }

    private val viewModel by viewModels<DrawingViewModel>()

    private fun handleTouchAt(event: MotionEvent): Boolean {
        val point = Point(event.x, event.y)
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> { viewModel.startLineAt(point); true }
            MotionEvent.ACTION_MOVE -> { viewModel.addToLine(point); true }
            MotionEvent.ACTION_UP -> { viewModel.endLineAt(point); true }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DRAGTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DrawingActivityLayout(
                        word = getWordFromIntent(),
                        viewModel = viewModel,
                        onTouch = {
                            if (viewModel.state.value == DrawingViewModel.State.DRAWING)
                                handleTouchAt(it)
                            else false
                        }
                    )
                }
            }
        }

        viewModel.maybeStartTimer(getTimerValueFromIntent())
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
