package edu.sempreacodar.drag.review.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.DrawingGuess
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun DrawingGuessLayout(drawingGuess: DrawingGuess, moveNumber: Int) {
    Column(
        Modifier.fillMaxWidth().padding(top = 32.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        WordViewRow(word = drawingGuess.word, number = moveNumber)
        DrawingView(drawing = drawingGuess.drawing)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DrawingGuessLayoutLayoutPreview() {
    DRAGTheme {
        DrawingGuessLayout(DrawingGuess("Pintainho", Drawing.EMPTY), 2)
    }
}