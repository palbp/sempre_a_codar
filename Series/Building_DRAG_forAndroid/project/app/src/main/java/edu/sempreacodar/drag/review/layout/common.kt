package edu.sempreacodar.drag.review.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.sempreacodar.drag.draw.NewDrawingCanvas
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun DrawingView(modifier: Modifier = Modifier, drawing: Drawing?) {
    NewDrawingCanvas(
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .clipToBounds(),
        drawing
    )
}

@Composable
fun WordViewRow(modifier: Modifier = Modifier, word: String, number: Int) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, ),
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommonPreview() {
    DRAGTheme {
        WordViewRow(word = "Palavra", number = 1)
        //DrawingView(drawing = null)
    }
}