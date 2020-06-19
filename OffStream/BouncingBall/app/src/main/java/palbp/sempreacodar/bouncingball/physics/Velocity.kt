package palbp.sempreacodar.bouncingball.physics

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
    }
}
