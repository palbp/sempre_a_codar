package palbp.sempreacodar.bouncingball

import androidx.annotation.WorkerThread
import palbp.sempreacodar.bouncingball.physics.Location
import palbp.sempreacodar.bouncingball.physics.Velocity

/**
 * A bouncing ball in a playground delimited by [Location.Origin] and [bounds].
 * Instances can be mutated and therefore they are not thread-safe. The current solution presumes
 * that the ball instance is confined to the engine's worker thread.
 *
 * @property    position    the current location
 * @property    velocity    the current velocity
 * @property    radius      the radius
 */
@WorkerThread
class BouncingBall(
    var position: Location,
    var velocity: Velocity,
    val radius: Float,
    private val bounds: Location) {

    private fun isInXBounds() = position.x - radius >= 0 && position.x + radius <= bounds.x
    private fun isInYBounds() = position.y - radius >= 0 && position.y + radius <= bounds.y

    /**
     * Moves the ball, reflecting it if a boundary has been reached.
     * Implementation note: the collision detection algorithm is overly simplified, to say the least
     */
    fun move(): BouncingBall {
        velocity = when {
            !isInXBounds() && isInYBounds() -> Velocity(-velocity.dx, velocity.dy)
            isInXBounds() && !isInYBounds() -> Velocity(velocity.dx, -velocity.dy)
            !isInXBounds() && !isInYBounds() -> Velocity(-velocity.dx, -velocity.dy)
            else -> velocity
        }
        position += velocity
        return this
    }
}
