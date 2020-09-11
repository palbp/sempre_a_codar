/**
 * Defines the representation of the ball.
 * @property center     The ball's location (its center).
 * @property radius     The ball's radius.
 * @property velocity   The ball's velocity.
 * @property deflection The ball's deflection, if one occurred.
 */
data class Ball(
        val center: Location,
        val radius: Double,
        val velocity: Velocity,
        val deflection: Deflection? = null
)

/**
 * Defines the representation for identifying the existing ball deflections
 */
enum class Deflection { BY_BAT, OTHER }

/**
 * Checks whether the ball is moving.
 * @param ball  The ball instance.
 * @return true if the ball is moving, false otherwise
 */
fun isBallMoving(ball: Ball) = ball.velocity.dx != 0.0 || ball.velocity.dy != 0.0

/**
 * Checks whether the given ball is within the specified bounds.
 * @param ball      The ball instance.
 * @param height    The height of the arena.
 * @return A boolean value indicating if [ball] is within the arena's bounds or not.
 */
private fun isBallInVerticalBounds(ball: Ball, height: Double) = ball.center.y - ball.radius >= 0 &&
        ball.center.y + ball.radius <= height

/**
 * Moves the ball within the specified bounds and with its velocity.
 * @param ball      The ball instance.
 * @param height    The height of the arena.
 * @return The moved ball.
 */
fun moveBall(ball: Ball, height: Double): Ball {
    val newBall = Ball(
            add(ball.center, ball.velocity),
            ball.radius,
            ball.velocity
    )

    return when {
        !isBallInVerticalBounds(newBall, height) -> {
            Ball(
                    if (newBall.velocity.dy < 0) Location(newBall.center.x, newBall.radius)
                    else Location(newBall.center.x, height - newBall.radius),
                    newBall.radius,
                    Velocity(newBall.velocity.dx, newBall.velocity.dy * -1),
                    Deflection.OTHER
            )
        }
        newBall.center.x - newBall.radius <= 0 -> {
            Ball(
                    Location(newBall.radius, newBall.center.y),
                    newBall.radius,
                    Velocity(newBall.velocity.dx * -1, newBall.velocity.dy),
                    Deflection.OTHER
            )
        }
        else -> newBall
    }
}

/**
 * Checks if the ball has entered the player's protected area, that is, if a loss should be
 * accounted for.
 * @param ball      The ball instance.
 * @param width     The width of the arena.
 */
fun isLoss(ball: Ball, width: Int) = ball.center.x + ball.radius >= width

