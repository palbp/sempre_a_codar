package edu.sempreacodar.drag.review

import android.view.MotionEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.sempreacodar.drag.model.Round
import edu.sempreacodar.drag.ui.theme.DRAGTheme

enum class SwipeDirection { LEFT, RIGHT }

/**
 * The composable that specifies the [RoundReviewActivity] layout
 */
@Composable
fun RoundReviewActivityLayout(
    roundLiveData: LiveData<Round?>,
    currentMoveIndexLiveData: LiveData<Int?>,
    onSwipe: (SwipeDirection) -> Unit
) {
    val round = roundLiveData.observeAsState()
    var startX = 0F
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                  when (it.action) {
                      MotionEvent.ACTION_DOWN -> { startX = it.x; true }
                      MotionEvent.ACTION_UP -> {
                          onSwipe(if (it.x - startX > 0) SwipeDirection.RIGHT else SwipeDirection.LEFT)
                          true
                      }
                      else -> false
                  }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val roundInfo = round.value
        if (roundInfo == null)
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        else
            MoveLayout(roundInfo, currentMoveIndexLiveData)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DRAGTheme {
        RoundReviewActivityLayout(MutableLiveData(), MutableLiveData(), { })
    }
}