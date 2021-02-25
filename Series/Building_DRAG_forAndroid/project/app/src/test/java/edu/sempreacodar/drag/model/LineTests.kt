package edu.sempreacodar.drag.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LineTests {

    @Test
    fun freshLine_hasNoPoints() {
        val sut = Line()
        assertTrue(sut.points.isEmpty())
    }

    @Test
    fun startNewLineAt_returnsALineWithASinglePoint() {
        val thePoint = Point(1F, 1F)
        val sut = startNewLineAt(thePoint)
        assertEquals(1, sut.points.size)
        assertEquals(thePoint, sut.points.first())
    }

    @Test
    fun addPointToEmptyLine_returnsLineWithOnePoint() {
        val emptyLine = Line()
        val sut = emptyLine + Point(1F, 1F)
        assertEquals(1, sut.points.size)
    }

    @Test
    fun addPointToNonEmptyLine_returnsLineWithNewPoint() {
        val line = Line(listOf(Point(1F, 1F)))
        val sut = line + Point(2F, 2F)
        assertEquals(2, sut.points.size)
        assertTrue(sut.points.contains(Point(2F, 2F)))
    }
}