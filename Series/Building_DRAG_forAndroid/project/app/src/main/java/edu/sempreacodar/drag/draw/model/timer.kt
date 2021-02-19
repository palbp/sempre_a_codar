package edu.sempreacodar.drag.draw.model

import java.lang.System.currentTimeMillis

/**
 * Represents the time remaining for making a game move (draw or guess)
 */
data class GameTimer(val minutes: Int = 0, val seconds: Int = 0) {

    companion object {
        fun fromSeconds(totalSeconds: Int) = GameTimer(
            minutes = totalSeconds / SECONDS_IN_MINUTE,
            seconds = totalSeconds % SECONDS_IN_MINUTE
        )
    }

    override fun toString() = "$minutes:${timerPartToString(seconds)}"
    fun toSeconds() = minutes * SECONDS_IN_MINUTE + seconds
}

/**
 * Gets the current system time, expressed in seconds (truncating the milliseconds)
 * @return the current system time, in seconds
 */
fun currentTimeSeconds() = currentTimeMillis() / 1000

/**
 * Internal helper function used to convert a timer part (minutes or seconds) to a two digit string.
 */
private fun timerPartToString(part: Int) = if (part < 10) "0$part" else part.toString()

private const val SECONDS_IN_MINUTE = 60

