package palbp.sempreacodar.snake.physics

/**
 * Velocity is represented as a 2D vector. Instances are immutable.
 *
 * @property    dx  the horizontal coordinate variation
 * @property    dy  the vertical coordinate variation
 */
data class Velocity(val dx: Float, val dy: Float) {
    companion object {
        /**
         * The velocity instance that corresponds to the Zero vector. It is used to
         * represent the absence of movement.
         */
        val Stopped = Velocity(0f, 0f)

        /**
         * Gets the velocity instance from the given vector.
         */
        fun of(vector: Vector2D) = Velocity(vector.x, vector.y)
    }

    /**
     * Converts the current velocity to its [Vector2D] counterpart
     */
    fun toVector2D() = Vector2D(dx, dy)
}
