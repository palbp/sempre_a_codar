package edu.sempreacodar.drag.draw

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.model.min
import edu.sempreacodar.drag.ui.theme.DRAGTheme

@Composable
fun NewDrawingActivityLayout(word: String, timer: GameTimer, drawing: Drawing?) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.wrapContentHeight().fillMaxWidth().padding(start = 32.dp, end = 32.dp, top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(word, style = TextStyle(fontSize = 24.sp))
            Text(timer.toString(), style = TextStyle(fontSize = 34.sp))
        }
        NewDrawingCanvas(
            modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(),
            drawing
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DRAGTheme {
        NewDrawingActivityLayout(word = "Pintainho", timer = 1.min, drawing = null)
    }
}