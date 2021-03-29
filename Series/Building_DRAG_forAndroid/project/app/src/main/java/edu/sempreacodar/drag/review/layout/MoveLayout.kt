package edu.sempreacodar.drag.review.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.DrawingGuess
import edu.sempreacodar.drag.model.Round
import edu.sempreacodar.drag.model.WordGuess
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun MoveLayout(round: Round, currentMoveIndexLiveData: LiveData<Int?>) {
    val currentMove = currentMoveIndexLiveData.observeAsState()
    currentMove.value?.let {
        when (val guess = round.moves[it]) {
            is DrawingGuess -> DrawingGuessLayout(drawingGuess = guess, moveNumber = it + 1)
            is WordGuess -> WordGuessLayout(wordGuess = guess, moveNumber = it + 1)
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