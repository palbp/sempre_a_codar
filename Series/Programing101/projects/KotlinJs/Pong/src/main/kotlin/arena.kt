import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Defines the representation of the game's arena.
 * @property bat        The player's bat.
 * @property ball       The ball.
 * @property width      The width of the arena.
 * @property height     The height of the arena.
 */
data class Arena(val bat: Bat, val ball: Ball, val width: Int, val height: Int)

/**
 * Creates an arena instance with the specified dimension.
 * @param width     The width of the arena.
 * @param height    The height of the arena.
 * @return The newly created arena instance.
 */
fun initializeArena(width: Int, height: Int): Arena {
    val ball = Ball(
            Location(width / 2.0, height / 2.0),
            5.0,
            Velocity(0.0, 0.0)
    )
    val bat = Bat(Location(width - 15.0, height / 2.0), 7.0, 80.0)
    return Arena(bat, ball, width, height)
}

/**
 * Generates the initial ball velocity.
 */
fun getInitialVelocity(): Velocity {
    val alpha = Random.nextDouble(-PI/6, PI/6)
    val magnitude = 15.0
    return Velocity(magnitude * cos(alpha), magnitude * sin(alpha))
}

/**
 * Computes the new arena according to its current state and the new location for the player's bat.
 * @param arena         The current arena instance.
 * @param batLocation   The location of the player's bat.
 */
fun doStep(arena: Arena, batLocation: Location): Arena {
    val bat = keepBatInArenaBounds(
            Bat(batLocation, 7.0, 80.0),
            arena.height.toDouble(),
            20.0
    )
    val ball = moveBall(arena.ball, arena.height.toDouble())
    return Arena(
            bat,
            if(isBatHittingBall(bat, ball)) deflectBall(bat, ball) else ball,
            arena.width,
            arena.height
    )
}

/**
 * Starts the movement of the ball if it has not started yet.
 * @param arena The arena instance.
 * @return The arena instance bearing a moving ball.
 */
fun start(arena: Arena) =
    if (isBallMoving(arena.ball)) arena
    else Arena(
            arena.bat,
            Ball(arena.ball.center, arena.ball.radius, getInitialVelocity()),
            arena.width,
            arena.height
    )
