package palbp.sempreacodar.snake

import androidx.annotation.WorkerThread
import palbp.sempreacodar.snake.physics.Location
import palbp.sempreacodar.snake.physics.Velocity

/**
 * A snake in an arena delimited by [Location.Origin] and [bounds].
 * Instances can be mutated and therefore they are not thread-safe. The current solution presumes
 * that the snake instance is confined to the engine's worker thread.
 *
 * @property    position    the current location
 * @property    velocity    the current velocity
 * @property    headRadius  the radius of the snake's head
 */
@WorkerThread
class Snake(
    var position: Location,
    var velocity: Velocity,
    val headRadius: Float,
    private val bounds: Location) {

    private fun collisionDetected() = position.x - headRadius <= 0 ||
            position.x + headRadius >= bounds.x ||
            position.y - headRadius <= 0 ||
            position.y + headRadius >= bounds.y

    /**
     * Moves the snake, stopping it if a boundary has been reached.
     * Implementation note: the collision detection algorithm is overly simplified, to say the least
     */
    fun move(): Snake {
        velocity = if (collisionDetected()) Velocity.Stopped else velocity
        position += velocity
        return this
    }
}
