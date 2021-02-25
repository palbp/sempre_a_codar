package edu.sempreacodar.drag.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameTimerTests {

    @Test
    fun fromSeconds_withPositiveValue_returnsCorrectInstance() {
        val sut = GameTimer.fromSeconds(90)
        assertNotNull(sut)
        assertEquals(1, sut.minutes)
        assertEquals(30, sut.seconds)
    }

    @Test
    fun toSeconds_returnsCorrectValue() {
        val sut = GameTimer.from(minutes = 1, seconds = 30)
        assertEquals(90, sut.toSeconds())
    }

    @Test
    fun toString_returnsStringWithExpectedFormat() {
        val sut1 = GameTimer.from(minutes = 1, seconds = 30)
        assertEquals("1:30", sut1.toString())
        val sut2 = GameTimer.from(minutes = 1, seconds = 3)
        assertEquals("1:03", sut2.toString())
    }
}