package edu.sempreacodar.drag.model

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
        val sut = Drawing.EMPTY
        assertTrue(sut.lines.isEmpty())
    }

    @Test
    fun addLineToEmptyDrawing_returnsDrawingWithOneLine() {
        val emptyDrawing = Drawing.EMPTY
        val sut = emptyDrawing + aLine
        assertEquals(0, sut.lines.size)
        assertEquals(aLine, sut.lastLine)
    }

    @Test
    fun addLineToNonEmptyDrawing_returnsDrawingWithNewLine() {
        val line = Line(listOf(Point(2F, 2F), Point(3F, 3F)))
        val drawing = Drawing.from(listOf(line))

        val sut = drawing + aLine
        assertEquals(1, sut.lines.size)
        assertEquals(sut.lastLine, aLine)
    }

    @Test
    fun addPointToLastLine_returnsDrawingWithPointInLastLine() {
        val line1 = Line(listOf(Point(2F, 2F), Point(3F, 3F)))
        val line2 = Line(listOf(Point(4F, 4F), Point(5F, 5F)))
        val drawing = Drawing.from(listOf(line1, line2))

        val thePoint = Point(0F, 0F)
        val sut = drawing.addToLastLine(thePoint)
        assertEquals(drawing.lines.size, sut.lines.size)
        assertEquals(drawing.lastLine?.points?.size?.plus(1), sut.lastLine?.points?.size)
        assertEquals(thePoint, sut.lastLine?.points?.last())
    }
}