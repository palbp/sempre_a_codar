package edu.sempreacodar.drag.draw.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DrawingTests {

    private val aLine = Line().apply { this + Point() + Point(1F, 1F) }

    @Test
    fun freshDrawing_hasNoLines() {
        val sut = Drawing()
        assertTrue(sut.lines.isEmpty())
    }

    @Test
    fun addLineToEmptyDrawing_returnsDrawingWithOneLine() {
        val emptyDrawing = Drawing()
        val sut = emptyDrawing + aLine
        assertEquals(1, sut.lines.size)
    }

    @Test
    fun addLineToNonEmptyDrawing_returnsDrawingWithNewLine() {
        val line = Line(listOf(Point(2F, 2F), Point(3F, 3F)))
        val drawing = Drawing(listOf(line))

        val sut = drawing + aLine
        Assert.assertEquals(2, sut.lines.size)
        assertTrue(sut.lines.contains(aLine))
    }

    @Test
    fun addPointToLastLine_returnsDrawingWithPointInLastLine() {
        val line1 = Line(listOf(Point(2F, 2F), Point(3F, 3F)))
        val line2 = Line(listOf(Point(4F, 4F), Point(5F, 5F)))
        val drawing = Drawing(listOf(line1, line2))

        val thePoint = Point(0F, 0F)
        val sut = drawing.addToLastLine(thePoint)
        assertEquals(drawing.lines.size, sut.lines.size)
        assertEquals(drawing.lines.last().points.size + 1, sut.lines.last().points.size)
        assertEquals(thePoint, sut.lines.last().points.last())
    }
}