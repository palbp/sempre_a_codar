package edu.sempreacodar.drag.draw.model

import edu.sempreacodar.drag.draw.Drawing
import edu.sempreacodar.drag.draw.Line
import edu.sempreacodar.drag.draw.Point
import edu.sempreacodar.drag.draw.plus
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DrawingTests {

    private val aLine = Line().apply { this + Point() + Point(1, 1) }

    @Test
    fun freshDrawing_hasNoLines() {
        val sut = Drawing()
        assertTrue(sut.lines.isEmpty())
    }

    @Test
    fun addLineToEmptyDrawing_returnsDrawingWithOneLine() {
        val emptyDrawing = Drawing()
        val sut = emptyDrawing + aLine
        Assert.assertEquals(1, sut.lines.size)
    }

    @Test
    fun addLineToNonEmptyDrawing_returnsDrawingWithNewLine() {
        val line = Line(listOf(Point(2, 2), Point(3, 3)))
        val drawing = Drawing(listOf(line))

        val sut = drawing + aLine
        Assert.assertEquals(2, sut.lines.size)
        assertTrue(sut.lines.contains(aLine))
    }
}