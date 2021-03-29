package edu.sempreacodar.drag.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameTimerTests {

    @Test
    fun fromSeconds_withNegativeValue_returnsZeroInstance() {
        val sut = GameTimer.fromSeconds(-90)
        assertNotNull(sut)
        assertEquals(0, sut.minutes)
        assertEquals(0, sut.seconds)
    }

    @Test
    fun from_withNegativeValue_returnsZeroInstance() {
        val sut = GameTimer.from(-2, -3)
        assertNotNull(sut)
        assertEquals(0, sut.minutes)
        assertEquals(0, sut.seconds)
    }

    @Test
    fun fromSeconds_withPositiveValue_returnsCorrectInstance() {
        val sut = GameTimer.fromSeconds(90)
        assertNotNull(sut)
        assertEquals(1, sut.minutes)
        assertEquals(30, sut.seconds)
    }

    @Test
    fun toSeconds_returnsCorrectValue() {
        val sut = 1.min + 30.sec
        assertEquals(90, sut.toSeconds())
    }

    @Test
    fun toString_returnsStringWithExpectedFormat() {
        val sut1 = GameTimer.from(minutes = 1, seconds = 30)
        assertEquals("1:30", sut1.toString())
        val sut2 = 1.min + 3.sec
        assertEquals("1:03", sut2.toString())
    }
}