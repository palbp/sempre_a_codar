package edu.sempreacodar.drag.review.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.WordGuess
import edu.sempreacodar.drag.ui.theme.DRAGTheme


@Composable
fun WordGuessLayout(wordGuess: WordGuess, moveNumber: Int) {
    ConstraintLayout {
        val (wordRow, drawing) = createRefs()
        WordViewRow(
            Modifier.constrainAs(wordRow) { bottom.linkTo(parent.bottom, margin = 16.dp) },
            word = wordGuess.word ?: "",
            number = moveNumber
        )
        DrawingView(
            Modifier.constrainAs(drawing) {
                top.linkTo(parent.top)
                bottom.linkTo(wordRow.top)
            }.padding(top = 32.dp, bottom = 32.dp),
            drawing = wordGuess.drawing
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WordGuessLayoutPreview() {
    DRAGTheme {
        WordGuessLayout(WordGuess("Galinha", Drawing.EMPTY), 1)
    }
}