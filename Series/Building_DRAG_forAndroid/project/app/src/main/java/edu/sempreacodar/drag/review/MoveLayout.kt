package edu.sempreacodar.drag.review

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.sempreacodar.drag.draw.NewDrawingCanvas
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.DrawingGuess
import edu.sempreacodar.drag.model.Round
import edu.sempreacodar.drag.model.WordGuess
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun DrawingView(drawing: Drawing?, isGuess: Boolean = false) {
    // TODO: create a visual difference between a guess and an input
    NewDrawingCanvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clipToBounds(),
        drawing
    )
}

@Composable
fun WordViewRow(word: String, number: Int, isGuess: Boolean = false) {
    // TODO: create a visual difference between a guess and an input
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = word,
            style = TextStyle(fontSize = 24.sp)
        )
        Text(
            text = number.toString(),
            style = TextStyle(fontSize = 34.sp)
        )
    }
}

@Composable
fun MoveLayout(round: Round, currentMoveIndexLiveData: LiveData<Int?>,
) {
    val currentMove = currentMoveIndexLiveData.observeAsState()
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        currentMove.value.let {
            if (it != null && it < round.moves.size) {
                when (val guess = round.moves[it]) {
                    is DrawingGuess -> {
                        WordViewRow(word = guess.word, number = it + 1)
                        DrawingView(drawing = guess.drawing, isGuess = true)
                    }
                    is WordGuess -> {
                        WordViewRow(word = guess.word ?: "", number = it + 1, isGuess = true)
                        DrawingView(drawing = guess.drawing)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoveLayoutPreview() {
    DRAGTheme {
        MoveLayout(
            Round(listOf(
                DrawingGuess("Pintainho", Drawing.EMPTY),
                WordGuess("Galinha", Drawing.EMPTY)
            )),
            MutableLiveData(0)
        )
    }
}