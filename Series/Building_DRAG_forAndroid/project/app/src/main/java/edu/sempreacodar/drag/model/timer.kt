package edu.sempreacodar.drag.model

import edu.sempreacodar.drag.model.GameTimer.Companion.from
import edu.sempreacodar.drag.model.GameTimer.Companion.fromSeconds
import java.lang.System.currentTimeMillis

/**
 * Represents the time remaining for making a game move (draw or guess)
 */
class GameTimer private constructor(val minutes: Int = 0, val seconds: Int = 0) {

    companion object {
        /**
         * Builds an instance from the specified number of seconds. Negative values are coerced to zero.
         * @param totalSeconds  the number of seconds of the created instance.
         * @return the new timer instance.
         */
        fun fromSeconds(totalSeconds: Int) = GameTimer(
            minutes = totalSeconds.coerceAtLeast(0) / SECONDS_IN_MINUTE,
            seconds = totalSeconds.coerceAtLeast(0) % SECONDS_IN_MINUTE
        )

        /**
         * Builds an instance from the specified number of minutes and seconds. Negative values
         * are coerced to zero.
         * @param minutes  the number of minutes
         * @param seconds  the number of seconds
         * @return the new timer instance.
         */
        fun from(minutes: Int, seconds: Int = 0) = GameTimer(
            minutes = minutes.coerceAtLeast(0)  +
                    seconds.coerceAtLeast(0) / SECONDS_IN_MINUTE,
            seconds = seconds.coerceAtLeast(0) % SECONDS_IN_MINUTE
        )
    }

    override fun toString() = "$minutes:${timerPartToString(seconds)}"
    fun toSeconds() = minutes * SECONDS_IN_MINUTE + seconds
}

/**
 * Extension property for expressing values in seconds
 */
inline val Int.sec: GameTimer
    get() = fromSeconds(this)

/**
 * Extension property for expressing values in minutes
 */
inline val Int.min: GameTimer
    get() = from(this)

/**
 * Addition operator overload
 */
operator fun GameTimer.plus(other: GameTimer) = fromSeconds(toSeconds() + other.toSeconds())

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

