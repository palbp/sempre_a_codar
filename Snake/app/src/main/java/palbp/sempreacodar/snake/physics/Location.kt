package palbp.sempreacodar.snake.physics

/**
 * Locations on the screen are represented as 2D vectors. Instances are immutable.
 *
 * @property    x   the horizontal coordinate
 * @property    y   the vertical coordinate
 */
data class Location(val x: Float, val y: Float) {

    companion object {
        /**
         * The location instance that corresponds to the Zero vector in a 2D plane. It is used to
         * represent the axis origin.
         */
        val Origin: Location = Location(0f, 0f)

        /**
         * Gets the location instance from the given vector.
         */
        fun of(vector: Vector2D) = Location(vector.x, vector.y)
    }

    /**
     * Operator overload that produces the [Location] instance that results from moving this
     * instance with the given velocity.
     * @param   [velocity]  the velocity to be applied to the location.
     * @return  the new location.
     */
    operator fun plus(velocity: Velocity) = Location(x + velocity.dx, y + velocity.dy)

    /**
     * Converts the current location to its [Vector2D] counterpart
     */
    fun toVector2D() = Vector2D(x, y)
}