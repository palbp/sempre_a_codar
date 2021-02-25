package edu.sempreacodar.drag.draw

import android.view.MotionEvent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.model.min
import edu.sempreacodar.drag.model.sec
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun NewDrawingActivityLayout(
    word: String,
    timer: LiveData<GameTimer>,
    drawing: LiveData<Drawing>,
    onTouch: (MotionEvent) -> Boolean
) {

    Column(
        Modifier.fillMaxWidth()
    ) {

        val drawingState: Drawing? by drawing.observeAsState()
        val timerState: GameTimer by timer.observeAsState(0.sec)

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(word, style = TextStyle(fontSize = 24.sp))
            Text(timerState.toString(), style = TextStyle(fontSize = 34.sp))
        }
        NewDrawingCanvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clipToBounds()
                .pointerInteropFilter { onTouch(it) },
            drawingState
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DRAGTheme {
        NewDrawingActivityLayout(
            word = "Pintainho",
            timer = MutableLiveData(1.min),
            drawing = MutableLiveData(null),
            onTouch = { false }
        )
    }
}