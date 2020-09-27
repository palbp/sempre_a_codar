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
 * Representation used to express the possible results of an animation step in the arena.
 */
enum class MoveResult {
    OK,
    PLAYER_LOSS,
    OPPONENT_LOSS
}


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
        else -> newBall
    }
}

/**
 * Checks if the ball either one of the protected areas, that is, if a loss should be
 * accounted for.
 * @param ball      The ball instance.
 * @param width     The width of the arena.
 */
fun checkMoveResult(ball: Ball, width: Int): MoveResult = when {
    ball.center.x + ball.radius >= width -> MoveResult.PLAYER_LOSS
    ball.center.x - ball.radius <= 0 -> MoveResult.OPPONENT_LOSS
    else -> MoveResult.OK
}

/**
 * Deflects the ball if it has hit the given line.
 * @param batEdge               The line used to verify if there is a ball collision.
 * @param ball                  The ball instance.
 * @param previousBallLocation  The previous location of the [ball].
 * @return the deflected ball or null.
*/
fun maybeDeflectBall(batEdge: Line, ball: Ball, previousBallLocation: Location): Ball? {
    // Using the algorithm described in the links to detect collision between two lines
    // http://paulbourke.net/geometry/pointlineplane/
    // http://www.jeffreythompson.org/collision-detection/line-line.php

    val ballMovementLine = Line(previousBallLocation, ball.center)
    val denominator = (batEdge.end.y - batEdge.start.y) * (ballMovementLine.end.x - ballMovementLine.start.x) - (batEdge.end.x - batEdge.start.x) * (ballMovementLine.end.y - ballMovementLine.start.y)
    val uA = ((batEdge.end.x - batEdge.start.x) * (ballMovementLine.start.y - batEdge.start.y) - (batEdge.end.y - batEdge.start.y) * (ballMovementLine.start.x - batEdge.start.x)) /
            denominator
    val uB = ((ballMovementLine.end.x - ballMovementLine.start.x) * (ballMovementLine.start.y - batEdge.start.y) - (ballMovementLine.end.y - ballMovementLine.start.y) * (ballMovementLine.start.x - batEdge.start.x)) /
            denominator

    return if (uA !in 0.0..1.0 || uB !in 0.0..1.0) null
    else {
        println("deflected by $batEdge")
        Ball(
                Location(
                        ballMovementLine.start.x + uA * (ballMovementLine.end.x - ballMovementLine.start.x) +
                                if (ball.velocity.dx < 0) ball.radius else -ball.radius,
                        ballMovementLine.start.y + uA * (ballMovementLine.end.y - ballMovementLine.start.y)
                ),
                ball.radius,
                Velocity(ball.velocity.dx * -1, ball.velocity.dy),
                Deflection.BY_BAT
        )
    }
}

