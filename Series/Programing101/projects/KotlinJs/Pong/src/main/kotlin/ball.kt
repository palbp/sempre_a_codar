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
 * Creates a stationary ball positioned at the center of the area with the specified dimensions.
 * @param width     The area width.
 * @param height    The area height.
 * @return The ball instance.
 */
fun createStationaryBall(width: Int, height: Int) = Ball(
        Location(width / 2.0, height / 2.0),
        5.0,
        Velocity(0.0, 0.0)
)

/**
 * Builds a new ball instance from the given one ([ball]) and with a new location [newCenter].
 * @param ball        The ball instance to be used as a prototype for the new instance.
 * @param newCenter   The location for the new instance.
 * @return A [Ball] instance with all its properties copied from [ball] (except its deflection property) and
 * positioned at [newCenter]. Notice that the new ball instance is considered not deflected.
 */
fun buildBallWith(ball: Ball, newCenter: Location) = Ball(
        newCenter, ball.radius, ball.velocity
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
private fun isBallInVerticalBounds(ball: Ball, height: Double) =
        ball.center.y - ball.radius >= 0 &&
                ball.center.y + ball.radius <= height

/**
 * Moves the ball within the specified bounds, deflecting it if those bounds are reached.
 * @param ball      The ball instance.
 * @param height    The height of the arena.
 * @return The moved ball.
 */
fun moveBall(ball: Ball, height: Double): Ball {

    fun deflectedLocation(ball: Ball): Location =
            if (ball.velocity.dy < 0) Location(ball.center.x, ball.radius)
            else Location(ball.center.x, height - ball.radius)

    val newBall = buildBallWith(ball, add(ball.center, ball.velocity))
    return when {
        !isBallInVerticalBounds(newBall, height) -> Ball(
                deflectedLocation(newBall),
                newBall.radius,
                Velocity(newBall.velocity.dx, newBall.velocity.dy * -1),
                Deflection.OTHER
        )
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

    fun adjustBallLocation(location: Location, ball: Ball) = Location(
            location.x + if (ball.velocity.dx < 0) ball.radius else -ball.radius,
            location.y
    )

    val intersectionPoint = computeIntersection(batEdge, Line(previousBallLocation, ball.center))
    return if (intersectionPoint != null) Ball(
            adjustBallLocation(intersectionPoint, ball),
            ball.radius,
            Velocity(ball.velocity.dx * -1, ball.velocity.dy),
            Deflection.BY_BAT
    )
    else null
}

