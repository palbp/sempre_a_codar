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
    }

    /**
     * Operator overload that produces the [Location] instance that results from moving the
     */
    operator fun plus(velocity: Velocity) = Location(x + velocity.dx, y + velocity.dy)
}