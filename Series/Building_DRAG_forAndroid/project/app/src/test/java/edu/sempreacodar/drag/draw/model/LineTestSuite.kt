package edu.sempreacodar.drag.draw.model

import edu.sempreacodar.drag.draw.Line
import edu.sempreacodar.drag.draw.Point
import edu.sempreacodar.drag.draw.plus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LineTestSuite {

    @Test
    fun freshLine_hasNoPoints() {
        val sut = Line()
        assertTrue(sut.points.isEmpty())
    }

    @Test
    fun addPointToEmptyLine_returnsLineWithOnePoint() {
        val emptyLine = Line()
        val sut = emptyLine + Point(1, 1)
        assertEquals(1, sut.points.size)
    }

    @Test
    fun addPointToNonEmptyLine_returnsLineWithNewPoint() {
        val line = Line(listOf(Point(1, 1)))
        val sut = line + Point(2, 2)
        assertEquals(2, sut.points.size)
        assertTrue(sut.points.contains(Point(2, 2)))
    }
}