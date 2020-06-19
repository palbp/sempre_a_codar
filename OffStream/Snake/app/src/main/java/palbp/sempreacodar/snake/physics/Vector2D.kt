package palbp.sempreacodar.snake.physics

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Represents vectors in a two dimensional world.
 */
class Vector2D(val x: Float, val y: Float) {

    companion object {
        val NullVector = Vector2D(0f, 0f)
    }

    /**
     * Gets the magnitude (a.k.a modulus) of the current instance.
     */
    fun magnitude(): Float = sqrt(x.pow(2) + y.pow(2))

    /**
     * Gets the unit vector for the current instance, that is, a vector with the same direction
     * but with magnitude 1.
     */
    fun unit(): Vector2D = Vector2D(x / magnitude(), y / magnitude())

    /**
     * Adds the given vector to current one.
     * @param [other]   the vector to be added to this instance.
     * @return  the resulting vector
     */
    infix operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)

    /**
     * Scales the current vector by the given value.
     * @param   [value] the value to multiply this instance for.
     * @return  the vector instance that results from scaling this instance by the given value.
     */
    infix operator fun times(value: Float) = Vector2D(x * value, y * value)

    /**
     * Gets the inverse vector of the current insatnce
     * @return  the inverse vector
     */
    operator fun unaryMinus() = this * -1f

    /**
     * Subtracts the current vector by the given vector.
     * @param   [other] the vector to be subtracted from this one.
     * @return  the resulting vector.
     */
    infix operator fun minus(other: Vector2D) = this + -other
}