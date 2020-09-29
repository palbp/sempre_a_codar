import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

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
 * Creates a ball positioned at the center of the area with the specified dimensions.
 * @param width     The area width.
 * @param height    The area height.
 * @return The ball instance.
 */
fun initializeBall(width: Int, height: Int) = Ball(
        Location(width / 2.0, height / 2.0),
        5.0,
        Velocity(0.0, 0.0)
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
 * Generates the initial ball velocity.
 */
fun getInitialVelocity(): Velocity {
    val alpha = Random.nextDouble(-PI / 5, PI / 5)
    val magnitude = 16.0
    return Velocity(magnitude * cos(alpha), magnitude * sin(alpha))
}

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
 * Deflects the ball if it has hit the given line.
 * @param batEdge               The line used to verify if there is a ball collision.
 * @param ball                  The ball instance.
 * @param previousBallLocation  The previous location of the [ball].
 * @return the deflected ball or null.
 */
fun maybeDeflectBall(batEdge: Line, ball: Ball, previousBallLocation: Location): Ball? {

    val intersectionPoint = computeIntersection(batEdge, Line(previousBallLocation, ball.center))
    return if (intersectionPoint != null) {
        Ball(
                Location(
                        intersectionPoint.x + if (ball.velocity.dx < 0) ball.radius else -ball.radius,
                        intersectionPoint.y
                ),
                ball.radius,
                Velocity(ball.velocity.dx * -1, ball.velocity.dy),
                Deflection.BY_BAT
        )
    }
    else null
}

